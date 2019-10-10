package com.yzhang.monsterhunterworldcompanion.appdatabase;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class LocationConverter {

    @TypeConverter
    public static List<Monster.Location> stringToList(String locations) {
        List<Monster.Location> locationList = new ArrayList<>();
        String[] location = locations.split(",");
        for(int i = 0; i < location.length; i++) {
            locationList.add(new Monster.Location(0, location[i].trim(), 0));
        }
        return locationList;
    }

    @TypeConverter
    public static String dateToString(List<Monster.Location> locations) {
        String location = "";
        for(int i = 0; i < locations.size(); i++) {
            if(i != 0) {
                location += ", ";
                location += locations.get(i).getName();
            } else {
                location += locations.get(i).getName();
            }
        }
        return location;
    }

}
