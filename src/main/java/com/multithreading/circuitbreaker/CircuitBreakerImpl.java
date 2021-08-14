package com.multithreading.circuitbreaker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.multithreading.circuitbreaker.State.*;

@Slf4j
public class CircuitBreakerImpl implements CircuitBreaker<Work> {
    private final ExecutorService executorService;
    private int errorsThreshold = 5;
    private long sleepWindow = 5000;
    private long timeOut = 1;

    private AtomicInteger numberOfFail = new AtomicInteger(0);
    private AtomicLong recentErrorTime;

    private volatile State state = CLOSED;

    public CircuitBreakerImpl(ExecutorService exs, long timeOut, long sleepWindow, int errorsTreshold) {
        this.executorService = exs;
        this.timeOut = timeOut;
        this.sleepWindow = sleepWindow;
        this.errorsThreshold = errorsTreshold;
    }

    public CircuitBreakerImpl(ExecutorService exs) {
        this.executorService = exs;
    }

    @Override
    public void execute(Work work) {
        log.info("State: {} : {} : {}", state, work.getId(), work.getWeight());

        if (state == OPEN) {
            if ((System.currentTimeMillis() - recentErrorTime.get()) > sleepWindow) {
                state = HALF_OPEN;
            } else {
                work.getFallback().perform(new CBException(state));
                return;
            }
        }
        try {
            CompletableFuture.supplyAsync(work.getTask(), executorService)
                    .orTimeout(timeOut, TimeUnit.SECONDS)
                    .thenAccept(e -> {
                        log.info("Success : {} : {}", work.getId(), work.getWeight());
                        state = CLOSED;
                        numberOfFail.set(0);
                    })
                    .exceptionally(e -> {
                        work.getFallback().perform(e);
                        onError((Exception) e);
                        return false;
                    });
        } catch (Exception e) {
            log.error(" Error : {} : {} : {} : {}", work.getId(), work.getWeight(), numberOfFail.get(), e.getMessage());

        }
    }

    private void onError(Exception ex) {
        numberOfFail.incrementAndGet();
        log.error("Errors : {}", numberOfFail);
        recentErrorTime.set(System.currentTimeMillis());
        if (state == HALF_OPEN || numberOfFail.get() >= errorsThreshold) {
            state = OPEN;
        }
    }

    @Override
    public void stop() {
        log.info("App shutdown");
        executorService.shutdown();
    }
}
