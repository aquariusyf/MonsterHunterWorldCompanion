package com.yzhang.monsterhunterworldcompanion.appdatabase.weapons;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class WeaponStatsConverter {

    @TypeConverter
    public static int attackToInteger(CommonMeleeWeapon.Attack attack) {
        return attack.getDisplay();
    }

    @TypeConverter
    public static CommonMeleeWeapon.Attack integerToAttack(int displayAttack) {
        return new CommonMeleeWeapon.Attack(displayAttack, 0);
    }

    @TypeConverter
    public static String attributeToString(CommonMeleeWeapon.Attribute attributes) {
        String attr = "";
        attr += attributes.getDamageType();
        attr += "@";
        attr += attributes.getAffinity();
        return attr;
    }

    @TypeConverter
    public static CommonMeleeWeapon.Attribute stringToAttribute(String attr) {
        String[] attrArray = attr.split("@");
        return new CommonMeleeWeapon.Attribute(attrArray[0], Integer.valueOf(attrArray[1]));
    }

    @TypeConverter
    public static String durabilityListToString(List<CommonMeleeWeapon.Durability> durability) {
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
    public static List<CommonMeleeWeapon.Durability> stringToDurabilityList(String durabilityString) {
        List<CommonMeleeWeapon.Durability> durabilityList = new ArrayList<>();
        String[] durabilityArray = durabilityString.split("@");
        for(String str: durabilityArray) {
            String[] sharpnessColors = str.split("&");
            CommonMeleeWeapon.Durability durability = new CommonMeleeWeapon.Durability(
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
    public static String slotListToString(List<CommonMeleeWeapon.Slot> slotList) {
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
    public static List<CommonMeleeWeapon.Slot> stringToSlotList(String slots) {
        String[] slotArray = slots.split("@");
        List<CommonMeleeWeapon.Slot> slotList = new ArrayList<>();
        for(String slot: slotArray) {
            slotList.add(new CommonMeleeWeapon.Slot(Integer.valueOf(slot)));
        }
        return slotList;
    }

    @TypeConverter
    public static String elementToString(CommonMeleeWeapon.Element element) {
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
    public static CommonMeleeWeapon.Element stringToElement(String elementString) {
        String[] elementArray = elementString.split("@");
        boolean hidden = elementArray[2].equals("1") ? true : false;
        return new CommonMeleeWeapon.Element(elementArray[0], Integer.valueOf(elementArray[1]), hidden);
    }

    @TypeConverter
    public static String assetsToString(CommonMeleeWeapon.Assets assets) {
        String assetString = "";
        assetString += assets.getIcon();
        assetString += "@#&";
        assetString += assets.getImage();
        return assetString;
    }

    @TypeConverter
    public static CommonMeleeWeapon.Assets stringToAssets(String assetString) {
        String[] assetArray = assetString.split("@#&");
        return new CommonMeleeWeapon.Assets(assetArray[0], assetArray[1]);
    }

}
