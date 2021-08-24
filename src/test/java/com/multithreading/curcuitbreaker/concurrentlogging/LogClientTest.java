package com.multithreading.curcuitbreaker.concurrentlogging;

import com.multithreading.concurrentlogging.LogClient;
import com.multithreading.concurrentlogging.LogClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static java.util.concurrent.CompletableFuture.*;

@Slf4j
public class LogClientTest {

    LogClient logClient;

    @Before
    public void setup() {
        log.info("setting");
        logClient = new LogClientImpl(5);
    }

    @Test
    public void verifyPositiveFlow() throws InterruptedException, ExecutionException, TimeoutException {
        logClient.append("1", 1, "Hi I am Sourabh");
        log.info(logClient.poll());
        logClient.append("1", 2, "I live in Bangkok");
        log.info(logClient.poll());
        logClient.append("2", 3, "Hi I am Rohan");
        log.info(logClient.poll());
        logClient.append("2", 4, "I live in Delhi");
        log.info(logClient.poll());
        logClient.end("2");
        log.info(logClient.poll());
        logClient.end("1");
        log.info(logClient.poll());
        logClient.append("3", 5, "Hi I am Peter");
        log.info(logClient.poll());
        logClient.append("3", 6, "I live in NY");
        log.info(logClient.poll());
        logClient.end("3");
        log.info(logClient.poll());
    }

    @Test
    public void verifyPositiveFlowAsync() throws InterruptedException, ExecutionException, TimeoutException {
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        runAsync(() -> logClient.append("1", 1, "Hi I am Sourabh"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.append("1", 2, "I live in Bangkok"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.append("2", 3, "Hi I am Rohan"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.append("2", 4, "I live in Delhi"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.end("2"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.end("1"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.append("3", 5, "Hi I am Peter"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.append("3", 6, "I live in NY"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        runAsync(() -> logClient.end("3"));
        tasks.add(runAsync(() -> log.info(logClient.poll())));
        allOf(tasks.toArray(CompletableFuture[]::new)).get();
    }
}
