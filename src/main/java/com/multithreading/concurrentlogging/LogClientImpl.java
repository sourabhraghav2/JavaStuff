package com.multithreading.concurrentlogging;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


@Slf4j
public class LogClientImpl implements LogClient {
    private final Integer timeOut;
    private final Map<String, Request> requests;
    private final ConcurrentSkipListMap<Long, String> queue;
    private final ReentrantLock lock;
    private final ConcurrentLinkedDeque<CompletableFuture> waitingPoll;

    public LogClientImpl(int timeOut) {
        this.timeOut = timeOut;
        this.requests = new ConcurrentHashMap<>();
        this.queue = new ConcurrentSkipListMap<>((a, b) -> (int) (a - b));
        this.lock = new ReentrantLock();
        this.waitingPoll = new ConcurrentLinkedDeque();
    }

    public void append(String pid, long timestamp, String message) {
        if (requests.containsKey(pid)) {
            lock.lock();
            requests.get(pid).getLogs().add(message);
            lock.unlock();
        } else {
            final var logs = new LinkedList<String>();
            logs.add(message);
            requests.put(pid, new Request(pid, System.currentTimeMillis(), false, logs));
            queue.put(timestamp, pid);
        }
    }

    public void end(String pid) {
        requests.get(pid).setEnded(true);
        if (!waitingPoll.isEmpty()) {
            waitingPoll.pop().complete(null);
        }
    }

    public String poll() {
        try {
            if (queue.isEmpty() || !requests.get(queue.firstEntry().getValue()).isEnded()) {
                final var res = new CompletableFuture<>();
                waitingPoll.add(res);
                res.get(3, TimeUnit.SECONDS);
            }
            if (!queue.isEmpty() && requests.get(queue.firstEntry().getValue()).isEnded() || requests.get(queue.firstEntry().getValue()).getStartTime() - System.currentTimeMillis() > timeOut * 1000) {
                lock.lock();
                final var pid = queue.firstEntry().getValue();
                final var response = requests.get(pid).getLogs();
                queue.pollFirstEntry();
                requests.remove(pid);
                return response.stream().collect(Collectors.joining(", "));
            }
            return null;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            waitingPoll.pop();
            return null;
        } finally {
            if (lock.isLocked())
                lock.unlock();
        }
    }
}

