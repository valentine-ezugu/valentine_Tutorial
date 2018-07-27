package com.valentine.factoryMethodPattern1;

public class FactoryRelease {

    private CarProduction carProduction;
    private String name;

    public FactoryRelease(CarProduction carProduction, String carName ) {
        this.carProduction =  carProduction;
        this.name = carName;
    }

    public String release() {
       return carProduction.produced(name);
    }

}
