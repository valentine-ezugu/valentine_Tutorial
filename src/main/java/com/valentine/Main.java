package com.valentine;

import com.valentine.factoryMethodPattern.PersistedFile;
import com.valentine.factoryMethodPattern.Sha256Encryptor;
import com.valentine.factoryMethodPattern1.BenzProduction;
import com.valentine.factoryMethodPattern1.CarProduction;
import com.valentine.factoryMethodPattern1.FactoryRelease;

public class Main {


    public static void main(String[] args) {

//         PersistedFile file = new PersistedFile("/foo/bar/text.txt", "Hello, world!", new Sha256Encryptor());
//         file.persist();

        FactoryRelease factoryRelease = new FactoryRelease(new BenzProduction("nnnn"), "BENZ");
        factoryRelease.release();
    }


}
