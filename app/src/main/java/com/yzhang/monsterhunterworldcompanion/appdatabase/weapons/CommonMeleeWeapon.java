package com.yzhang.monsterhunterworldcompanion.appdatabase.weapons;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "commonMeleeWeapon")
public class CommonMeleeWeapon {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "rarity")
    private int rarity;
    @ColumnInfo(name = "attack")
    private Attack attack;
    @ColumnInfo(name = "elderseal")
    private String elderseal;
    @ColumnInfo(name = "attributes")
    private Attribute attributes;
    @ColumnInfo(name = "durability")
    private List<Durability> durability;
    @ColumnInfo(name = "slots")
    private List<Slot> slots;
    @ColumnInfo(name = "elements")
    private Element elements;
    @ColumnInfo(name = "assets")
    private Assets assets;


    public static class Attack {

        private int display;
        private int raw;

        public Attack(int display, int raw) {
            this.display = display;
            this.raw = raw;
        }

        public int getDisplay() {
            return display;
        }

        public int getRaw() {
            return raw;
        }

    }

    public static class Attribute {

        private String damageType;
        private int affinity;

        public Attribute(String damageType, int affinity) {
            this.damageType = damageType;
            this.affinity = affinity;
        }

        public String getDamageType() {
            return damageType;
        }

        public int getAffinity() {
            return affinity;
        }

    }

    public static class Durability {

        private int red;
        private int orange;
        private int yellow;
        private int green;
        private int blue;
        private int white;

        public Durability(int red, int orange, int yellow, int green, int blue, int white) {
            this.red = red;
            this.orange = orange;
            this.yellow = yellow;
            this.green = green;
            this.blue = blue;
            this.white = white;
        }

        public int getRed() {
            return red;
        }

        public int getOrange() {
            return orange;
        }

        public int getYellow() {
            return yellow;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }

        public int getWhite() {
            return white;
        }

    }

    public static class Slot {

        private int rank;

        public Slot(int rank) {
            this.rank = rank;
        }

        public int getRank() {
            return rank;
        }

    }

    public static class Element {

        private String type;
        private int damage;
        private boolean hidden;

        public Element(String type, int damage, boolean hidden) {
            this.type = type;
            this.damage = damage;
            this.hidden = hidden;
        }

        public String getType() {
            return type;
        }

        public int getDamage() {
            return damage;
        }

        public boolean getHidden() {
            return hidden;
        }

    }

    public static class Assets {

        private String icon;
        private String image;

        public Assets(String icon, String image) {
            this.icon = icon;
            this.image = image;
        }

        public String getIcon() {
            return icon;
        }

        public String getImage() {
            return image;
        }

    }

}
