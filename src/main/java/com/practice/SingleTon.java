package com.practice;

public class SingleTon {
    public static void main(String[] args) {

    }

}

class Single {
    private Single() {
    }

    static Single instance;

    Single getInstance() {
        if (instance != null) {
            instance = new Single();
        }
        return instance;
    }
}
