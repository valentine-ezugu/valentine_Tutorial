package com.valentine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class StreamsApi {

    Map<String, String> myMap = new HashMap<String, String>();

    public Map<String, String> getMyMap() {
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


    public static List<String> returnStream( Map<String, String> myMap ) {
      return  myMap.entrySet().stream().
            filter(entry -> !entry.getKey().isEmpty()).
            map(entry -> entry.getKey() + "=" + entry.getValue()).collect(toList());
    }


}