package com.multithreading.curcuitbreaker;


import com.multithreading.circuitbreaker.CircuitBreaker;
import com.multithreading.circuitbreaker.CircuitBreakerImpl;
import com.multithreading.circuitbreaker.Work;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CircuitBreakerTest {

    @Before
    public void setup() {
        log.info("setting");
    }


    @Test
    public void verify() throws InterruptedException {
        List<Integer> list = List.of(6, 2, 7, 1, 4, 7, 5, 6, 8, 1, 9, 3, 1, 4, 7, 5, 6, 8, 1, 6, 3, 1, 4, 7, 9, 6, 8, 1, 2, 9, 1, 4, 7, 5, 6, 8);
        final var executor = new ThreadPoolExecutor(4, 6, 15, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        CircuitBreaker cb = new CircuitBreakerImpl(executor, 5, 5, 5);
        int count = 0;
        for (int i : list) {
            if (i == 1)
                Thread.sleep(2000);
            int finalCount = ++count;
            cb.execute(new Work(finalCount, i, () -> {
                log.info("Performing task : {} : {}", finalCount, i);
                try {
                    Thread.sleep(i * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i;
            }, (e) -> {
                final var msg = "Failed : " + finalCount + " : " + i + " : " + e.toString();
                log.info(msg);
                return msg;
            }));

        }
        cb.stop();
    }
}
