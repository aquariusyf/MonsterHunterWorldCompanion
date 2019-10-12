package com.yzhang.monsterhunterworldcompanion.appdatabase.armorset;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "armorSet")
public class ArmorSet {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "rank")
    private String rank;
    @ColumnInfo(name = "totalDefence")
    private int totalDefence;
    @ColumnInfo(name = "thunderRes")
    private int thunderRes;
    @ColumnInfo(name = "fireRes")
    private int fireRes;
    @ColumnInfo(name = "iceRes")
    private int iceRes;
    @ColumnInfo(name = "waterRes")
    private int waterRes;
    @ColumnInfo(name = "dragonRes")
    private int dragonRes;

    public ArmorSet(
            int primaryId,
            int id,
            String name,
            String rank,
            int totalDefence,
            int thunderRes,
            int fireRes,
            int iceRes,
            int waterRes,
            int dragonRes) {
        this.primaryId = primaryId;
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.totalDefence = totalDefence;
        this.thunderRes = thunderRes;
        this.fireRes = fireRes;
        this.iceRes = iceRes;
        this.waterRes = waterRes;
        this.dragonRes = dragonRes;
    }

    @Ignore
    public ArmorSet(
            int id,
            String name,
            String rank,
            int totalDefence,
            int thunderRes,
            int fireRes,
            int iceRes,
            int waterRes,
            int dragonRes) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.totalDefence = totalDefence;
        this.thunderRes = thunderRes;
        this.fireRes = fireRes;
        this.iceRes = iceRes;
        this.waterRes = waterRes;
        this.dragonRes = dragonRes;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getTotalDefence() {
        return totalDefence;
    }

    public void setTotalDefence(int totalDefence) {
        this.totalDefence = totalDefence;
    }

    public int getThunderRes() {
        return thunderRes;
    }

    public void setThunderRes(int thunderRes) {
        this.thunderRes = thunderRes;
    }

    public int getFireRes() {
        return fireRes;
    }

    public void setFireRes(int fireRes) {
        this.fireRes = fireRes;
    }

    public int getIceRes() {
        return iceRes;
    }

    public void setIceRes(int iceRes) {
        this.iceRes = iceRes;
    }

    public int getWaterRes() {
        return waterRes;
    }

    public void setWaterRes(int waterRes) {
        this.waterRes = waterRes;
    }

    public int getDragonRes() {
        return dragonRes;
    }

    public void setDragonRes(int dragonRes) {
        this.dragonRes = dragonRes;
    }

}
