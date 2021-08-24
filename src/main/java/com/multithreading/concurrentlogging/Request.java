package com.multithreading.concurrentlogging;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public
class Request {
    private String id;
    private long startTime;
    private boolean isEnded;
    private List<String> logs;
}