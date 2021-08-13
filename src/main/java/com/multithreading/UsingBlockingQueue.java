package com.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

public class UsingBlockingQueue {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);
        final var producer = new Thread(() -> {
            try {
                for (int i = 0; i < 100; ++i) {
                    queue.put(i);
                    System.out.println("Produced : " + i);
                }
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }
        });
        final var consumer = new Thread(() -> {
            try {
                while (true)
                    System.out.println("Consumed : " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        producer.start();
        consumer.start();
    }
}
