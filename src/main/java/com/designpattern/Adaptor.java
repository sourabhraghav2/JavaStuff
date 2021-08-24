package com.designpattern;

public class Adaptor {
    public static void main(String[] args) {
        NewPen p = new NewPen();
        System.out.println(p.write());
    }
}

interface PenInterface {
    public String write();

}

class NewPen implements PenInterface {

    @Override
    public String write() {
        return new OldPen().write();
    }

}

class OldPen {
    public String write() {
        return "Smoot writing with pilotpen ";
    }
}
