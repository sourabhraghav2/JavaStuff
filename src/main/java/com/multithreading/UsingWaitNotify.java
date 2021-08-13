package com.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class UsingWaitNotify {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        final var maxLimit = 10;
        final var producer = new Thread(() -> {
            for (int i = 0; i < 100; ++i) {
                synchronized (queue) {
                    while (queue.size() == maxLimit) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(i);
                    System.out.println("Produced : " + i);
                    queue.notify();
                }
            }
        });
        final var consumer = new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consume : " + queue.poll());
                    queue.notify();
                }
            }
        });
        producer.start();
        consumer.start();
    }

}
