package com.valentine.adapterDesignPattern1;

public class Main {

    public static void  main (String[] args) {

        Sparrow bird = new Sparrow();
        bird.makeSound();

        //Adapter wrapped the Bird object
        ToyDuck birdAdapter = new BirdAdapter(bird);
        birdAdapter.squeek();

    }

}
