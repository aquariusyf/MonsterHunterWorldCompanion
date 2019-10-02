package com.yzhang.monsterhunterworldcompanion.appdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "monster")
public class Monster {

    @PrimaryKey(autoGenerate = true)
    int idPrimary;
    @ColumnInfo(name = "id")
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "species")
    String species;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "icon")
    String icon;

    public Monster(int idPrimary, int id, String name, String species, String description, String icon) {
        this.idPrimary = idPrimary;
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
        this.icon = icon;
    }

    @Ignore
    public Monster(int id, String name, String species, String description, String icon) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
        this.icon = icon;
    }

    public int getIdPrimary() {
        return idPrimary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIdPrimary(int idPrimary) {
        this.idPrimary = idPrimary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
