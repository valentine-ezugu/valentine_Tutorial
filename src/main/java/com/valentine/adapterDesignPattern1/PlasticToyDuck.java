package com.valentine.adapterDesignPattern1;

/**
 * we have one implementation of ToyDuck
 */
class PlasticToyDuck implements ToyDuck {

    @Override
    public void squeek() {
        System.out.println("Squeak");
    }
}