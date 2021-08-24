package com.practice;

public class Decorator {
    public static void main(String[] args) {
        Pen pen = new FancyPen(new RedPen());
        System.out.println(pen.write());
    }
}

interface Pen {
    public String write();
}

class RedPen implements Pen {
    @Override
    public String write() {
        return "red";
    }
}

class FancyPen implements Pen {
    Pen pen;

    FancyPen(Pen pen) {
        this.pen = pen;
    }

    @Override
    public String write() {
        return "{" + pen.write() + "}";
    }
}