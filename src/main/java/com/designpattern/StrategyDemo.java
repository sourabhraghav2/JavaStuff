package com.designpattern;

public class StrategyDemo {
    public static void main(String[] args) {
        Ecexutor ex = new Ecexutor(new Add());
        System.out.println(ex.execute(1, 3));
        ex = new Ecexutor(new Mul());
        System.out.println(ex.execute(1, 3));
    }
}

interface Strategy {
    int perform(int a, int b);
}

class Add implements Strategy {
    @Override
    public int perform(int a, int b) {
        return a + b;
    }
}

class Mul implements Strategy {

    @Override
    public int perform(int a, int b) {
        return a * b;
    }
}

class Ecexutor {
    Strategy strategy;

    Ecexutor(Strategy strategy) {
        this.strategy = strategy;
    }

    int execute(int a, int b) {
        return strategy.perform(a, b);
    }
}