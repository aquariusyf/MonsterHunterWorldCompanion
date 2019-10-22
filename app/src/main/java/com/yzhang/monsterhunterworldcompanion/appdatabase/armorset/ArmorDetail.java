package com.yzhang.monsterhunterworldcompanion.appdatabase.armorset;

import android.util.Pair;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity(tableName = "armorDetail")
public class ArmorDetail {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;
    @ColumnInfo(name = "armorSetId")
    private int armorSetId;
    @ColumnInfo(name = "armorSetName")
    private String armorSetName;

    @ColumnInfo(name = "setSkillName")
    private String setSkillName;
    @ColumnInfo(name = "setSkills")
    private String setSkills;
    @ColumnInfo(name = "setSkillDescription")
    private String setSkillDescription;

    @ColumnInfo(name = "helmName")
    private String helmName;
    @ColumnInfo(name = "helmSkills")
    private String helmSkills;
    @ColumnInfo(name = "helmSlots")
    private String helmSlots;

    @ColumnInfo(name = "mailName")
    private String mailName;
    @ColumnInfo(name = "mailSkills")
    private String mailSkills;
    @ColumnInfo(name = "mailSlots")
    private String mailSlots;

    @ColumnInfo(name = "armName")
    private String armName;
    @ColumnInfo(name = "armSkills")
    private String armSkills;
    @ColumnInfo(name = "armSlots")
    private String armSlots;

    @ColumnInfo(name = "waistName")
    private String waistName;
    @ColumnInfo(name = "waistSkills")
    private String waistSkills;
    @ColumnInfo(name = "waistSlots")
    private String waistSlots;

    @ColumnInfo(name = "legName")
    private String legName;
    @ColumnInfo(name = "legSkills")
    private String legSkills;
    @ColumnInfo(name = "legSlots")
    private String legSlots;

    public ArmorDetail(
            int primaryId,
            int armorSetId,
            String armorSetName,
            String setSkillName,
            String setSkills,
            String setSkillDescription,
            String helmName,
            String helmSkills,
            String helmSlots,
            String mailName,
            String mailSkills,
            String mailSlots,
            String armName,
            String armSkills,
            String armSlots,
            String waistName,
            String waistSkills,
            String waistSlots,
            String legName,
            String legSkills,
            String legSlots) {
        this.primaryId = primaryId;
        this.armorSetId = armorSetId;
        this.armorSetName = armorSetName;
        this.setSkillName = setSkillName;
        this.setSkills = setSkills;
        this.setSkillDescription = setSkillDescription;
        this.helmName = helmName;
        this.helmSkills = helmSkills;
        this.helmSlots = helmSlots;
        this.mailName = mailName;
        this.mailSkills = mailSkills;
        this.mailSlots = mailSlots;
        this.armName = armName;
        this.armSkills = armSkills;
        this.armSlots = armSlots;
        this.waistName = waistName;
        this.waistSkills = waistSkills;
        this.waistSlots = waistSlots;
        this.legName = legName;
        this.legSkills = legSkills;
        this.legSlots = legSlots;
    }

    @Ignore
    public ArmorDetail(
            int armorSetId,
            String armorSetName,
            String setSkillName,
            String setSkills,
            String setSkillDescription,
            String helmName,
            String helmSkills,
            String helmSlots,
            String mailName,
            String mailSkills,
            String mailSlots,
            String armName,
            String armSkills,
            String armSlots,
            String waistName,
            String waistSkills,
            String waistSlots,
            String legName,
            String legSkills,
            String legSlots) {
        this.armorSetId = armorSetId;
        this.armorSetName = armorSetName;
        this.setSkillName = setSkillName;
        this.setSkills = setSkills;
        this.setSkillDescription = setSkillDescription;
        this.helmName = helmName;
        this.helmSkills = helmSkills;
        this.helmSlots = helmSlots;
        this.mailName = mailName;
        this.mailSkills = mailSkills;
        this.mailSlots = mailSlots;
        this.armName = armName;
        this.armSkills = armSkills;
        this.armSlots = armSlots;
        this.waistName = waistName;
        this.waistSkills = waistSkills;
        this.waistSlots = waistSlots;
        this.legName = legName;
        this.legSkills = legSkills;
        this.legSlots = legSlots;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public int getArmorSetId() {
        return armorSetId;
    }

    public String getArmorSetName() {
        return armorSetName;
    }

    public String getSetSkillName() {
        return setSkillName;
    }

    public List<Pair<String, String>> getSetSkillList() {
        List<Pair<String, String>> setSkillList = new ArrayList<>();
        String[] rawData = this.setSkills.split("@");
        List<String> nameList = new ArrayList<>();
        List<String> pieceNumList = new ArrayList<>();
        for(int i = 0; i < rawData.length; i++) {
            if(i%2 == 0) {
                nameList.add(rawData[i]);
            } else {
                pieceNumList.add(rawData[i]);
            }
        }
        for(int j = 0; j < nameList.size(); j++) {
            Pair<String, String> pair = new Pair<>(nameList.get(j), pieceNumList.get(j));
            setSkillList.add(pair);
        }
        return setSkillList;
    }

    public List<String> getSetSkillDescriptionList() {
        String[] rawData = this.setSkillDescription.split("@");
        return Arrays.asList(rawData);
    }

    public String getHelmName() {
        return helmName;
    }

    public List<Pair<String, String>> getHelmSkillList() {
        List<Pair<String, String>> skillList = new ArrayList<>();
        if(this.helmSkills == null || this.helmSkills.isEmpty()) {
            return null;
        }
        String[] rawData = this.helmSkills.split("@");
        List<String> nameList = new ArrayList<>();
        List<String> levelList = new ArrayList<>();
        for(int i = 0; i < rawData.length; i++) {
            if(i%2 == 0) {
                nameList.add(rawData[i]);
            } else {
                levelList.add(rawData[i]);
            }
        }
        for(int j = 0; j < nameList.size(); j++) {
            Pair<String, String> pair = new Pair<>(nameList.get(j), levelList.get(j));
            skillList.add(pair);
        }
        return skillList;
    }

    public List<String> getHelmSlotList() {
        if(this.helmSlots == null || this.helmSlots.isEmpty()) {
            return null;
        }
        String[] rawData = this.helmSlots.split("@");
        List<String> slots = Arrays.asList(rawData);
        Collections.sort(slots, Collections.reverseOrder());
        return slots;
    }

    public String getMailName() {
        return mailName;
    }

    public List<Pair<String, String>> getMailSkillList() {
        List<Pair<String, String>> skillList = new ArrayList<>();
        if(this.mailSkills == null || this.mailSkills.isEmpty()) {
            return null;
        }
        String[] rawData = this.mailSkills.split("@");
        List<String> nameList = new ArrayList<>();
        List<String> levelList = new ArrayList<>();
        for(int i = 0; i < rawData.length; i++) {
            if(i%2 == 0) {
                nameList.add(rawData[i]);
            } else {
                levelList.add(rawData[i]);
            }
        }
        for(int j = 0; j < nameList.size(); j++) {
            Pair<String, String> pair = new Pair<>(nameList.get(j), levelList.get(j));
            skillList.add(pair);
        }
        return skillList;
    }

    public List<String> getMailSlotList() {
        if(this.mailSlots == null || this.mailSlots.isEmpty()) {
            return null;
        }
        String[] rawData = this.mailSlots.split("@");
        List<String> slots = Arrays.asList(rawData);
        Collections.sort(slots, Collections.reverseOrder());
        return slots;
    }

    public String getArmName() {
        return armName;
    }

    public List<Pair<String, String>> getArmSkillList() {
        List<Pair<String, String>> skillList = new ArrayList<>();
        if(this.armSkills == null || this.armSkills.isEmpty()) {
            return null;
        }
        String[] rawData = this.armSkills.split("@");
        List<String> nameList = new ArrayList<>();
        List<String> levelList = new ArrayList<>();
        for(int i = 0; i < rawData.length; i++) {
            if(i%2 == 0) {
                nameList.add(rawData[i]);
            } else {
                levelList.add(rawData[i]);
            }
        }
        for(int j = 0; j < nameList.size(); j++) {
            Pair<String, String> pair = new Pair<>(nameList.get(j), levelList.get(j));
            skillList.add(pair);
        }
        return skillList;
    }

    public List<String> getArmSlotList() {
        if(this.armSlots == null || this.armSlots.isEmpty()) {
            return null;
        }
        String[] rawData = this.armSlots.split("@");
        List<String> slots = Arrays.asList(rawData);
        Collections.sort(slots, Collections.reverseOrder());
        return slots;
    }

    public String getWaistName() {
        return waistName;
    }

    public List<Pair<String, String>> getWaistSkillList() {
        List<Pair<String, String>> skillList = new ArrayList<>();
        if(this.waistSkills == null || this.waistSkills.isEmpty()) {
            return null;
        }
        String[] rawData = this.waistSkills.split("@");
        List<String> nameList = new ArrayList<>();
        List<String> levelList = new ArrayList<>();
        for(int i = 0; i < rawData.length; i++) {
            if(i%2 == 0) {
                nameList.add(rawData[i]);
            } else {
                levelList.add(rawData[i]);
            }
        }
        for(int j = 0; j < nameList.size(); j++) {
            Pair<String, String> pair = new Pair<>(nameList.get(j), levelList.get(j));
            skillList.add(pair);
        }
        return skillList;
    }

    public List<String> getWaistSlotList() {
        if(this.waistSlots == null || this.waistSlots.isEmpty()) {
            return null;
        }
        String[] rawData = this.waistSlots.split("@");
        List<String> slots = Arrays.asList(rawData);
        Collections.sort(slots, Collections.reverseOrder());
        return slots;
    }

    public String getLegName() {
        return legName;
    }

    public List<Pair<String, String>> getLegSkillList() {
        List<Pair<String, String>> skillList = new ArrayList<>();
        if(this.legSkills == null || this.legSkills.isEmpty()) {
            return null;
        }
        String[] rawData = this.legSkills.split("@");
        List<String> nameList = new ArrayList<>();
        List<String> levelList = new ArrayList<>();
        for(int i = 0; i < rawData.length; i++) {
            if(i%2 == 0) {
                nameList.add(rawData[i]);
            } else {
                levelList.add(rawData[i]);
            }
        }
        for(int j = 0; j < nameList.size(); j++) {
            Pair<String, String> pair = new Pair<>(nameList.get(j), levelList.get(j));
            skillList.add(pair);
        }
        return skillList;
    }

    public List<String> getLegSlotList() {
        if(this.legSlots == null || this.legSlots.isEmpty()) {
            return null;
        }
        String[] rawData = this.legSlots.split("@");
        List<String> slots = Arrays.asList(rawData);
        Collections.sort(slots, Collections.reverseOrder());
        return slots;
    }

    public String getSetSkills() {
        return setSkills;
    }

    public String getSetSkillDescription() {
        return setSkillDescription;
    }

    public String getHelmSkills() {
        return helmSkills;
    }

    public String getHelmSlots() {
        return helmSlots;
    }

    public String getMailSkills() {
        return mailSkills;
    }

    public String getMailSlots() {
        return mailSlots;
    }

    public String getArmSkills() {
        return armSkills;
    }

    public String getArmSlots() {
        return armSlots;
    }

    public String getWaistSkills() {
        return waistSkills;
    }

    public String getWaistSlots() {
        return waistSlots;
    }

    public String getLegSkills() {
        return legSkills;
    }

    public String getLegSlots() {
        return legSlots;
    }

}
