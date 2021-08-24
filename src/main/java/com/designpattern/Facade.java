package com.designpattern;

public class Facade {
    public static void main(String[] args) {
        CarMaker cm = new CarMaker();
        cm.getToyota().design();
        cm.getBmw().design();
    }
}

class CarMaker {
    Car bmw;
    Car toyota;

    public CarMaker() {
        this.bmw = new BMW();
        this.toyota = new Toyota();
    }

    public Car getBmw() {
        return bmw;
    }

    public Car getToyota() {
        return toyota;
    }
}

class Toyota implements Car {
    @Override
    public void design() {
        System.out.println("Toyota design");
    }
}
