package com.multithreading.circuitbreaker;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Supplier;

@Data
@AllArgsConstructor
public class Work {
    private int id;
    private int weight;
    private Supplier task;
    private Action fallback;
}
