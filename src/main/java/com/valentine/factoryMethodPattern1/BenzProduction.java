package com.valentine.factoryMethodPattern1;

/**
 *
 */
public class BenzProduction extends CarProduction {

    private String name;

    public BenzProduction(String name) {
        this.name = name;
    }

    @Override
    Car getProductionType(String name) {
        return new BENZ(name);
    }

}
