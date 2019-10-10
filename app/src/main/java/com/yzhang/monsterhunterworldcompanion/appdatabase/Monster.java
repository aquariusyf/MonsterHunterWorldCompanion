package com.yzhang.monsterhunterworldcompanion.appdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

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
    int icon;
    @ColumnInfo(name = "locations")
    List<Location> locations;

    public Monster(int idPrimary, int id, String name, String species, String description, int icon, List<Location> locations) {
        this.idPrimary = idPrimary;
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
        this.icon = icon;
        this.locations = locations;
    }

    @Ignore
    public Monster(int id, String name, String species, String description, int icon, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
        this.icon = icon;
        this.locations = locations;
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

    public int getIcon() {
        return icon;
    }

    public List<Location> getLocations() { return locations; }

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

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setLocations(List<Location> locations) { this.locations = locations; }

    public static class Location {
        int id;
        String name;
        int zoneCount;

        public Location(int id, String name, int zoneCount) {
            this.id = id;
            this.name = name;
            this.zoneCount = zoneCount;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getZoneCount() {
            return zoneCount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setZoneCount(int zoneCount) {
            this.zoneCount = zoneCount;
        }
    }

}
