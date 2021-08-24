package com.multithreading.concurrentlogging;

/**
 * Polls the first log entry of a completed process sorted by the start time of processes in the below format
 * {processId} started at {startTime} and ended at {endTime}
 * <p>
 * process id = 1 --> 12, 15
 * process id = 2 --> 8, 12
 * process id = 3 --> 7, 19
 * <p>
 * {3} started at {7} and ended at {19}
 * {2} started at {8} and ended at {12}
 * {1} started at {12} and ended at {15}
 */
public interface LogClient {
    void append(String processId, long timestamp, String message);

    void end(String processId);

    String poll() ;
}