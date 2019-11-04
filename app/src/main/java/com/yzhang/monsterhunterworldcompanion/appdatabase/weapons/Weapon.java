package com.yzhang.monsterhunterworldcompanion.appdatabase.weapons;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "weapon")
public class Weapon {

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
    @ColumnInfo(name = "shelling")
    private Shelling shelling;
    @ColumnInfo(name = "phial")
    private Phial phial;
    @ColumnInfo(name = "boostType")
    private String boostType;
    @ColumnInfo(name = "deviation")
    private String deviation;
    @ColumnInfo(name = "ammo")
    private List<Ammo> ammo;
    @ColumnInfo(name = "coatings")
    private List<String> coatings;
    @ColumnInfo(name = "durability")
    private List<Durability> durability;
    @ColumnInfo(name = "slots")
    private List<Slot> slots;
    @ColumnInfo(name = "elements")
    private List<Element> elements;
    @ColumnInfo(name = "assets")
    private Assets assets;

    public Weapon(
            int primaryId,
            int id,
            String name,
            String type,
            int rarity,
            Attack attack,
            String elderseal,
            Attribute attributes,
            Shelling shelling,
            Phial phial,
            String boostType,
            String deviation,
            List<Ammo> ammo,
            List<String> coatings,
            List<Durability> durability,
            List<Slot> slots,
            List<Element> elements,
            Assets assets) {
        this.primaryId = primaryId;
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.attack = attack;
        this.elderseal = elderseal;
        this.attributes = attributes;
        this.shelling = shelling;
        this.phial = phial;
        this.boostType = boostType;
        this.deviation = deviation;
        this.ammo = ammo;
        this.coatings = coatings;
        this.durability = durability;
        this.slots = slots;
        this.elements = elements;
        this.assets = assets;
    }

    @Ignore
    public Weapon(
            int id,
            String name,
            String type,
            int rarity,
            Attack attack,
            String elderseal,
            Attribute attributes,
            Shelling shelling,
            Phial phial,
            String boostType,
            String deviation,
            List<Ammo> ammo,
            List<String> coatings,
            List<Durability> durability,
            List<Slot> slots,
            List<Element> elements,
            Assets assets) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.attack = attack;
        this.elderseal = elderseal;
        this.attributes = attributes;
        this.shelling = shelling;
        this.phial = phial;
        this.boostType = boostType;
        this.deviation = deviation;
        this.ammo = ammo;
        this.coatings = coatings;
        this.durability = durability;
        this.slots = slots;
        this.elements = elements;
        this.assets = assets;
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

    public String getType() {
        return type;
    }

    public int getRarity() {
        return rarity;
    }

    public Attack getAttack() {
        return attack;
    }

    public String getElderseal() {
        return elderseal;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    public Shelling getShelling() {
        return shelling;
    }

    public Phial getPhial() {
        return phial;
    }

    public String getBoostType() {
        return boostType;
    }

    public String getDeviation() {
        return deviation;
    }

    public List<Ammo> getAmmo() {
        return ammo;
    }

    public List<String> getCoatings() {
        return coatings;
    }

    public List<Durability> getDurability() {
        return durability;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public List<Element> getElements() {
        return elements;
    }

    public Assets getAssets() {
        return assets;
    }

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

    public static class Shelling {

        private String type;
        private int level;

        public Shelling(String type, int level) {
            this.type = type;
            this.level = level;
        }

        public String getType() {
            return type;
        }

        public int getLevel() {
            return level;
        }

    }

    public static class Phial {

        private String type;
        private String damage;

        public Phial(String type, String damage) {
            this.type = type;
            this.damage = damage;
        }

        public String getType() {
            return type;
        }

        public String getDamage() {
            return damage;
        }

    }

    public static class Ammo {

        private String type;
        private List<Integer> capacities;

        public Ammo(String type, List<Integer> capacities) {
            this.type = type;
            this.capacities = capacities;
        }

        public String getType() {
            return type;
        }

        public List<Integer> getCapacity() {
            return capacities;
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
