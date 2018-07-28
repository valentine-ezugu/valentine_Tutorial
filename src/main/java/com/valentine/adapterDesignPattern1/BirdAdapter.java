package com.valentine.adapterDesignPattern1;

/**
 * Here we want to have another ToyDuck but in a case where it is forbidden
 * We can use an adapter class to create another
 * from  the interface of Bird since the have similar characteristics
 */
public class BirdAdapter implements ToyDuck {

   private Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }


    @Override
    public void squeek() {
        bird.makeSound();
    }

}
