package com.valentine.factoryMethodPattern1;

public abstract class CarProduction {

    public String produced(String carName) {

       Car type = getProductionType(carName);
        return type.produceCar();
    }

    abstract Car getProductionType(String carName);

}
