package com.valentine.adapterDesignPattern1;


public class Sparrow implements Bird {

    @Override
    public void fly() {
        System.out.println("fly like a sparrow"
        );
    }

    @Override
    public void makeSound() {
        System.out.println("chirp chirp");
    }

}
