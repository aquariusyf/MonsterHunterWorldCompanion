package com.yzhang.monsterhunterworldcompanion.appdatabase.ailment;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ailment")
public class Ailment {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;

    public Ailment(int primaryId, int id, String name, String description) {
        this.primaryId = primaryId;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public Ailment(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
