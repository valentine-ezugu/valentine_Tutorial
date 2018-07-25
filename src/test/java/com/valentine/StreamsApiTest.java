package com.valentine;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;


public class StreamsApiTest {

    private List<String> myList() {

        List<String> url = asList("currency=BYN",
            "currencyId=533067",
            "total=1605",
            "residences=0",
            "seatSet=Available",
            "charge=0",
            "premiumEconom=seatSet",
            "No=charge",
            "price=261.3",
            "baseHotel=value",
            "earlyBooking=value",
            "onlineConfirm=value");
        return url;
    }

    /**
     * use LinkedHasMap to keep order as it was entered
     *
     * @return
     */
    private Map<String, String> getMyMap() {
        Map<String, String> myMap = new LinkedHashMap<>();
        myMap.put("currency", "BYN");
        myMap.put("currencyId", "533067");
        myMap.put("total", "1605");
        myMap.put("residences", "0");
        myMap.put("seatSet", "Available");
        myMap.put("charge", "0");
        myMap.put("premiumEconom", "seatSet");
        myMap.put("No", "charge");
        myMap.put("price", "261.3");
        myMap.put("baseHotel", "value");
        myMap.put("earlyBooking", "value");
        myMap.put("onlineConfirm", "value");
        return myMap;
    }

    private static List<String> returnStream(Map<String, String> myMap) {
        return myMap.entrySet().stream().
            filter(entry -> !entry.getKey().isEmpty()).
            map(entry -> entry.getKey() + "=" + entry.getValue()).collect(toList());
    }

    @Test
    public void method() {
        Assert.assertEquals(myList(), returnStream(getMyMap()));
    }


}
