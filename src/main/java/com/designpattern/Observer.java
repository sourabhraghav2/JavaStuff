package com.designpattern;

import java.util.ArrayList;

import java.util.List;

public class Observer {
    public static void main(String[] args) {
        Topic topic = new Topic();

        new OctalListener(topic);
        new BinaryListener(topic);

        System.out.println("First state change: 15");
        topic.setState(15);
        System.out.println("Second state change: 10");
        topic.setState(10);
    }
}

abstract class Observable {
    protected Topic subject;

    public abstract void update();
}

class BinaryListener extends Observable {

    public BinaryListener(Topic subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

class OctalListener extends Observable {

    public OctalListener(Topic subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}

class Topic {

    private List<Observable> observers = new ArrayList<Observable>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        for (Observable observer : observers) {
            observer.update();
        }
    }

    public void attach(Observable observer) {
        observers.add(observer);
    }


}