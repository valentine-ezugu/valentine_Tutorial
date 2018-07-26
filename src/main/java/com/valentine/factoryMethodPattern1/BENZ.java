package com.valentine.factoryMethodPattern1;

public class BENZ implements Car {

    private String name;

    public BENZ(String name) {
        this.name = name;
    }

    @Override
    public String produceCar() {
        return name;
    }
}
