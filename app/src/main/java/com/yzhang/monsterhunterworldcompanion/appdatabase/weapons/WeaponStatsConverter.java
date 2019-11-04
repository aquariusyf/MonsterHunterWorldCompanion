package com.yzhang.monsterhunterworldcompanion.appdatabase.weapons;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponStatsConverter {

    @TypeConverter
    public static int attackToInteger(Weapon.Attack attack) {
        return attack.getDisplay();
    }

    @TypeConverter
    public static Weapon.Attack integerToAttack(int displayAttack) {
        return new Weapon.Attack(displayAttack, 0);
    }

    @TypeConverter
    public static String attributeToString(Weapon.Attribute attributes) {
        if(attributes == null) {
            return "";
        }
        String attr = "";
        attr += attributes.getDamageType();
        attr += "@";
        attr += attributes.getAffinity();
        return attr;
    }

    @TypeConverter
    public static Weapon.Attribute stringToAttribute(String attr) {
        if(attr == null || attr.isEmpty()) {
            return null;
        }
        String[] attrArray = attr.split("@");
        return new Weapon.Attribute(attrArray[0], Integer.valueOf(attrArray[1]));
    }

    @TypeConverter
    public static String shellingToString(Weapon.Shelling shelling) {
        if(shelling == null) {
            return "";
        }
        String shellingStr = "";
        shellingStr += shelling.getType();
        shellingStr += "@";
        shellingStr += shelling.getLevel();
        return shellingStr;
    }

    @TypeConverter
    public static Weapon.Shelling stringToShelling(String shellingStr) {
        if(shellingStr == null || shellingStr.isEmpty()) {
            return null;
        }
        String[] shellingArray = shellingStr.split("@");
        return new Weapon.Shelling(shellingArray[0], Integer.valueOf(shellingArray[1]));
    }

    @TypeConverter
    public static String phialToString(Weapon.Phial phial) {
        if(phial == null) {
            return "";
        }
        String phialStr = phial.getType();
        return phialStr;
    }

    @TypeConverter
    public static Weapon.Phial stringToPhial(String phialStr) {
        if(phialStr == null || phialStr.isEmpty()) {
            return null;
        }
        return new Weapon.Phial(phialStr, null);
    }

    @TypeConverter
    public static String ammoToString(List<Weapon.Ammo> ammo) {
        if(ammo == null || ammo.isEmpty()) {
            return "";
        }
        String ammoStr = "";
        for(int i = 0; i < ammo.size(); i++) {
            if(i == 0) {
                ammoStr += ammo.get(i).getType();
                ammoStr += "&";
                List<Integer> capacity = ammo.get(i).getCapacity();
                for(int j = 0; j < capacity.size(); j++) {
                    if(j != capacity.size() - 1) {
                        ammoStr += capacity.get(j);
                        ammoStr += "&";
                    } else {
                        ammoStr += capacity.get(j);
                    }
                }
            } else {
                ammoStr += "@";
                ammoStr += ammo.get(i).getType();
                ammoStr += "&";
                List<Integer> capacity = ammo.get(i).getCapacity();
                for(int j = 0; j < capacity.size(); j++) {
                    if(j < capacity.size() - 1) {
                        ammoStr += capacity.get(j);
                        ammoStr += "&";
                    } else {
                        ammoStr += capacity.get(j);
                    }
                }
            }
        }
        return ammoStr;
    }

    @TypeConverter
    public static List<Weapon.Ammo> stringToAmmo(String ammoStr) {
        if(ammoStr == null || ammoStr.isEmpty()) {
            return null;
        }
        String[] ammoArray = ammoStr.split("@");
        List<Weapon.Ammo> ammo = new ArrayList<>();
        for(int i = 0; i < ammoArray.length; i++) {
            String[] ammoDetailStr = ammoArray[i].split("&");
            List<Integer> capacity = new ArrayList<>();
            capacity.add(Integer.valueOf(ammoDetailStr[1]));
            capacity.add(Integer.valueOf(ammoDetailStr[2]));
            capacity.add(Integer.valueOf(ammoDetailStr[3]));
            ammo.add(new Weapon.Ammo(ammoDetailStr[0], capacity));
        }
        return ammo;
    }

    @TypeConverter
    public static String coatingToString(List<String> coatings) {
        if(coatings == null || coatings.isEmpty()) {
            return "";
        }
        String coatingStr = "";
        for(int i = 0; i < coatings.size(); i++) {
            if(i == 0) {
                coatingStr += coatings.get(i);
            } else {
                coatingStr += "@";
                coatingStr += coatings.get(i);
            }
        }
        return coatingStr;
    }

    @TypeConverter
    public static List<String> stringToCoatings(String coatingStr) {
        if(coatingStr == null || coatingStr.isEmpty()) {
            return null;
        }
        String[] coatingArray = coatingStr.split("@");
        return Arrays.asList(coatingArray);
    }

    @TypeConverter
    public static String durabilityListToString(List<Weapon.Durability> durability) {
        if(durability == null || durability.isEmpty()) {
            return "";
        }
        String durabilityString = "";
        for(int i = 0; i < durability.size(); i++) {
            if(i == 0) {
                durabilityString += durability.get(i).getRed();
                durabilityString += "&";
                durabilityString += durability.get(i).getOrange();
                durabilityString += "&";
                durabilityString += durability.get(i).getYellow();
                durabilityString += "&";
                durabilityString += durability.get(i).getGreen();
                durabilityString += "&";
                durabilityString += durability.get(i).getBlue();
                durabilityString += "&";
                durabilityString += durability.get(i).getWhite();
            } else {
                durabilityString += "@";
                durabilityString += durability.get(i).getRed();
                durabilityString += "&";
                durabilityString += durability.get(i).getOrange();
                durabilityString += "&";
                durabilityString += durability.get(i).getYellow();
                durabilityString += "&";
                durabilityString += durability.get(i).getGreen();
                durabilityString += "&";
                durabilityString += durability.get(i).getBlue();
                durabilityString += "&";
                durabilityString += durability.get(i).getWhite();
            }
        }
        return durabilityString;
    }

    @TypeConverter
    public static List<Weapon.Durability> stringToDurabilityList(String durabilityString) {
        if(durabilityString == null || durabilityString.isEmpty()) {
            return null;
        }
        List<Weapon.Durability> durabilityList = new ArrayList<>();
        String[] durabilityArray = durabilityString.split("@");
        for(String str: durabilityArray) {
            String[] sharpnessColors = str.split("&");
            Weapon.Durability durability = new Weapon.Durability(
                    Integer.valueOf(sharpnessColors[0]),
                    Integer.valueOf(sharpnessColors[1]),
                    Integer.valueOf(sharpnessColors[2]),
                    Integer.valueOf(sharpnessColors[3]),
                    Integer.valueOf(sharpnessColors[4]),
                    Integer.valueOf(sharpnessColors[5]));
            durabilityList.add(durability);
        }
        return durabilityList;
    }

    @TypeConverter
    public static String slotListToString(List<Weapon.Slot> slotList) {
        if(slotList == null || slotList.isEmpty()) {
            return "";
        }
        String slots = "";
        for(int i = 0; i <slotList.size(); i++) {
            if(i == 0) {
                slots += slotList.get(i).getRank();
            } else {
                slots += "@";
                slots += slotList.get(i).getRank();
            }
        }
        return slots;
    }

    @TypeConverter
    public static List<Weapon.Slot> stringToSlotList(String slots) {
        if(slots == null || slots.isEmpty()) {
            return null;
        }
        String[] slotArray = slots.split("@");
        List<Weapon.Slot> slotList = new ArrayList<>();
        for(String slot: slotArray) {
            slotList.add(new Weapon.Slot(Integer.valueOf(slot)));
        }
        return slotList;
    }

    @TypeConverter
    public static String elementToString(List<Weapon.Element> elementList) {
        if(elementList == null || elementList.isEmpty()) {
            return "";
        }
        Weapon.Element element = elementList.get(0);
        String elementString = "";
        elementString += element.getType();
        elementString += "@";
        elementString += element.getDamage();
        elementString += "@";
        if(element.getHidden()) {
            elementString += 1;
        } else {
            elementString += 0;
        }
        return elementString;
    }

    @TypeConverter
    public static List<Weapon.Element> stringToElement(String elementString) {
        if(elementString == null || elementString.isEmpty()) {
            return null;
        }
        List<Weapon.Element> elementList = new ArrayList<>();
        String[] elementArray = elementString.split("@");
        boolean hidden = elementArray[2].equals("1") ? true : false;
        elementList.add(new Weapon.Element(elementArray[0], Integer.valueOf(elementArray[1]), hidden));
        return elementList;
    }

    @TypeConverter
    public static String assetsToString(Weapon.Assets assets) {
        if(assets == null) {
            return "";
        }
        String assetString = "";
        assetString += assets.getIcon();
        assetString += "@#&";
        assetString += assets.getImage();
        return assetString;
    }

    @TypeConverter
    public static Weapon.Assets stringToAssets(String assetString) {
        if(assetString == null || assetString.isEmpty()) {
            return null;
        }
        String[] assetArray = assetString.split("@#&");
        return new Weapon.Assets(assetArray[0], assetArray[1]);
    }

}
