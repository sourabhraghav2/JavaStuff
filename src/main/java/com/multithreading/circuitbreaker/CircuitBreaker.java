package com.multithreading.circuitbreaker;

public interface CircuitBreaker<W extends Work> {
    public void execute(W supplier);

    public void stop();
}
