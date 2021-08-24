package com.practice;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {
    public static void main(String[] args) {
        Topic t = new Topic();
        new Left(t);
        new Right(t);
        t.onChange("Hi");
    }
}

abstract class Observable {
    Topic topic;

    abstract void update();

}

class Left extends Observable {
    Left(Topic topic) {
        this.topic = topic;
        topic.add(this);
    }

    @Override
    void update() {
        System.out.println("Left : " + topic.getData());
    }
}

class Right extends Observable {
    Right(Topic topic) {
        this.topic = topic;
        topic.add(this);
    }

    @Override
    void update() {
        System.out.println("Right : " + topic.getData());
    }
}

class Topic {
    List<Observable> list;
    String data;

    String getData() {
        return data;
    }

    Topic() {
        list = new ArrayList<>();
    }

    void add(Observable o) {
        list.add(o);
    }

    void onChange(String data) {
        this.data = data;
        for (var e : list) e.update();
    }

}