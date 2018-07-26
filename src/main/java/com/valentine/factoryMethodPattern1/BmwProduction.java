package com.valentine.factoryMethodPattern1;

import org.elasticsearch.common.recycler.Recycler;

public class BmwProduction extends CarProduction {

    @Override
    Car getProductionType(String carName) {
        return new BMW(carName);
    }
}
