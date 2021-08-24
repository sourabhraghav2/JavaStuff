package com.practice;

public class Adaptor {
    public static void main(String[] args) {
        Pen pen = new NewPen(new OldPen());
        System.out.println(pen.write());
    }

}

class OldPen implements Pen {

    @Override
    public String write() {
        return "old";
    }
}

class NewPen implements Pen {
    Pen pen;

    NewPen(Pen pen) {
        this.pen = pen;
    }

    @Override
    public String write() {
        return pen.write();
    }
}

