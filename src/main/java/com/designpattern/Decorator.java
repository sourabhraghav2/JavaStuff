package com.designpattern;

public class Decorator {
    public static void main(String[] args) {
        Maruti maruti = new Maruti();
        maruti.design();
        System.out.println("---------------------");

        BMW bmw = new BMW();
        bmw.design();
        System.out.println("---------------------");

        LooksDecorator newLookBMW = new LooksDecorator(bmw);
        newLookBMW.design();
        System.out.println("---------------------");

        LooksDecorator newLookMaruti = new LooksDecorator(maruti);
        newLookMaruti.design();
        System.out.println("---------------------");

    }
}

class LooksDecorator implements Car {
    Car car;

    public LooksDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void design() {
        System.out.println("Modified looks");
        car.design();
        System.out.println("High end interior");
    }
}

interface Car {
    void design();
}

class Maruti implements Car {

    @Override
    public void design() {
        System.out.println("4 Type + chassis");
    }

}

class BMW implements Car {

    @Override
    public void design() {
        System.out.println("BMW");
    }
}