package com.valentine.factoryMethodPattern1;

public class BMW implements Car {

    private String name;

    public BMW(String name) {
        this.name = name;
    }

    @Override
    public String produceCar() {
        return name;
    }

}
