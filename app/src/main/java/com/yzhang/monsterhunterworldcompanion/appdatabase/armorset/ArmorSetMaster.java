package com.yzhang.monsterhunterworldcompanion.appdatabase.armorset;

import java.util.List;

/** Armor set class */
public class ArmorSetMaster {

    private static final String HELM = "head";
    private static final String MAIL = "chest";
    private static final String ARM = "gloves";
    private static final String WAIST = "waist";
    private static final String LEG = "legs";

    private int id;
    private String name;
    private String rank;
    private List<ArmorPiece> pieces;
    private Bonus bonus;
    private int totalDefence;
    private int thunderRes;
    private int fireRes;
    private int iceRes;
    private int waterRes;
    private int dragonRes;

    public ArmorSetMaster(int id, String name, String rank, List<ArmorPiece> pieces, Bonus bonus) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.pieces = pieces;
        this.bonus = bonus;
        totalDefence = 0;
        thunderRes = 0;
        fireRes = 0;
        iceRes = 0;
        waterRes = 0;
        dragonRes = 0;
    }

    public static void calculateTotalDefenceStats(ArmorSetMaster armorSetMaster) {
        for(ArmorPiece armorPiece: armorSetMaster.pieces) {
            armorSetMaster.totalDefence += armorPiece.getDefense().getBase();
            armorSetMaster.thunderRes += armorPiece.getResistances().getThunder();
            armorSetMaster.fireRes += armorPiece.getResistances().getFire();
            armorSetMaster.iceRes += armorPiece.getResistances().getIce();
            armorSetMaster.waterRes += armorPiece.getResistances().getWater();
            armorSetMaster.dragonRes += armorPiece.getResistances().getDragon();
        }
    }

    public static String getSetSkillName(ArmorSetMaster armorSetMaster) {
        if(armorSetMaster.getBonus() == null || armorSetMaster.getBonus().getRanks().isEmpty()) {
            return "";
        }
        return armorSetMaster.getBonus().getName();
    }

    public static String getSetSkills(ArmorSetMaster armorSetMaster) {
        if(armorSetMaster.getBonus() == null || armorSetMaster.getBonus().getRanks().isEmpty()) {
            return "";
        }
        String setSkills = "";
        List<Bonus.Rank> rankList = armorSetMaster.getBonus().getRanks();
        for(int i = 0; i < rankList.size(); i++) {
            setSkills += rankList.get(i).getSkill().getSkillName();
            setSkills += "@";
            setSkills += rankList.get(i).getPieces();
            if(i != rankList.size() - 1) {
                setSkills += "@";
            }
        }
        return setSkills;
    }

    public static String getSetSkillDescription(ArmorSetMaster armorSetMaster) {
        if(armorSetMaster.getBonus() == null || armorSetMaster.getBonus().getRanks().isEmpty()) {
            return "";
        }
        String setSkillDescriptions = "";
        List<Bonus.Rank> rankList = armorSetMaster.getBonus().getRanks();
        for(int i = 0; i < rankList.size(); i++) {
            setSkillDescriptions += rankList.get(i).getSkill().getDescription();
            if(i != rankList.size() - 1) {
                setSkillDescriptions += "@";
            }
        }
        return setSkillDescriptions;
    }

    public static String getArmorPieceName(ArmorSetMaster armorSetMaster, String type) {
        ArmorPiece armorPiece = getArmorPieceByType(armorSetMaster, type);
        if(armorPiece == null) {
            return "";
        }
        return armorPiece.getName();
    }

    public static String getArmorPieceSkills(ArmorSetMaster armorSetMaster, String type) {
        ArmorPiece armorPiece = getArmorPieceByType(armorSetMaster, type);
        String skills = "";
        if(armorPiece == null || armorPiece.getSkills() == null || armorPiece.getSkills().isEmpty()) {
            return skills;
        }
        for(int i = 0; i < armorPiece.getSkills().size(); i++) {
            skills += armorPiece.getSkills().get(i).getSkillName();
            skills += "@";
            skills += armorPiece.getSkills().get(i).getLevel();
            if(i != armorPiece.getSkills().size() - 1) {
                skills += "@";
            }
        }
        return skills;
    }

    public static String getArmorPieceSlots(ArmorSetMaster armorSetMaster, String type) {
        ArmorPiece armorPiece = getArmorPieceByType(armorSetMaster, type);
        String slots = "";
        if(armorPiece == null || armorPiece.getSlots() == null || armorPiece.getSlots().isEmpty()) {
            return slots;
        }
        for(int i = 0; i < armorPiece.getSlots().size(); i++) {
            slots += armorPiece.getSlots().get(i).getRank();
            if(i != armorPiece.getSlots().size() - 1) {
                slots += "@";
            }
        }
        return slots;
    }

    private static ArmorPiece getArmorPieceByType(ArmorSetMaster armorSetMaster, String type) {
        switch (type) {
            case HELM:
                for(ArmorPiece armorPiece: armorSetMaster.getPieces()) {
                    if(armorPiece.getType().equals(HELM)) {
                        return armorPiece;
                    }
                }
            case MAIL:
                for(ArmorPiece armorPiece: armorSetMaster.getPieces()) {
                    if(armorPiece.getType().equals(MAIL)) {
                        return armorPiece;
                    }
                }
            case ARM:
                for(ArmorPiece armorPiece: armorSetMaster.getPieces()) {
                    if(armorPiece.getType().equals(ARM)) {
                        return armorPiece;
                    }
                }
            case WAIST:
                for(ArmorPiece armorPiece: armorSetMaster.getPieces()) {
                    if(armorPiece.getType().equals(WAIST)) {
                        return armorPiece;
                    }
                }
            case LEG:
                for(ArmorPiece armorPiece: armorSetMaster.getPieces()) {
                    if(armorPiece.getType().equals(LEG)) {
                        return armorPiece;
                    }
                }
            default: return null;
        }
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

    public List<ArmorPiece> getPieces() {
        return pieces;
    }

    public void setPieces(List<ArmorPiece> pieces) {
        this.pieces = pieces;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
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

    /** Armor piece inner class */
    public static class ArmorPiece {

        private int id;
        private String name;
        private String type;
        private String rank;
        private int rarity;
        private Defence defense;
        private Resistance resistances;
        private List<Slot> slots;
        private List<ArmorSkill> skills;

        public ArmorPiece(
                int id,
                String name,
                String type,
                String rank,
                int rarity,
                Defence defense,
                Resistance resistances,
                List<Slot> slots,
                List<ArmorSkill> skills) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.rank = rank;
            this.rarity = rarity;
            this.defense = defense;
            this.resistances = resistances;
            this.slots = slots;
            this.skills = skills;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getRarity() {
            return rarity;
        }

        public void setRarity(int rarity) {
            this.rarity = rarity;
        }

        public Defence getDefense() {
            return defense;
        }

        public void setDefense(Defence defense) {
            this.defense = defense;
        }

        public Resistance getResistances() {
            return resistances;
        }

        public void setResistances(Resistance resistances) {
            this.resistances = resistances;
        }

        public List<Slot> getSlots() {
            return slots;
        }

        public void setSlots(List<Slot> slots) {
            this.slots = slots;
        }

        public List<ArmorSkill> getSkills() {
            return skills;
        }

        public void setSkills(List<ArmorSkill> skills) {
            this.skills = skills;
        }

        /** Defence inner class */
        public static class Defence {

            private int base;
            private int max;
            private int augmented;

            public Defence(int base, int max, int augmented) {
                this.base = base;
                this.max = max;
                this.augmented = augmented;
            }

            public int getBase() {
                return base;
            }

            public void setBase(int base) {
                this.base = base;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getAugmented() {
                return augmented;
            }

            public void setAugmented(int augmented) {
                this.augmented = augmented;
            }

        }

        /** Resistance inner class */
        public static class Resistance {

            private int fire;
            private int water;
            private int ice;
            private int thunder;
            private int dragon;

            public Resistance(int fire, int water, int ice, int thunder, int dragon) {
                this.fire = fire;
                this.water = water;
                this.ice = ice;
                this.thunder = thunder;
                this.dragon = dragon;
            }

            public int getFire() {
                return fire;
            }

            public void setFire(int fire) {
                this.fire = fire;
            }

            public int getWater() {
                return water;
            }

            public void setWater(int water) {
                this.water = water;
            }

            public int getIce() {
                return ice;
            }

            public void setIce(int ice) {
                this.ice = ice;
            }

            public int getThunder() {
                return thunder;
            }

            public void setThunder(int thunder) {
                this.thunder = thunder;
            }

            public int getDragon() {
                return dragon;
            }

            public void setDragon(int dragon) {
                this.dragon = dragon;
            }

        }

        /** Slot inner class */
        public static class Slot {

            private int rank;

            public Slot(int rank) {
                this.rank = rank;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

        }

        /** Armor skill inner class */
        public static class ArmorSkill {

            private int id;
            private int level;
            private String description;
            private String skillName;

            public ArmorSkill(int id, int level, String description, String skillName) {
                this.id = id;
                this.level = level;
                this.description = description;
                this.skillName = skillName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSkillName() {
                return skillName;
            }

            public void setSkillName(String skillName) {
                this.skillName = skillName;
            }

        }

    }

    /** Bonus inner class */
    public static class Bonus {

        private int id;
        private String name;
        private List<Rank> ranks;

        public Bonus(int id, String name, List<Rank> ranks) {
            this.id = id;
            this.name = name;
            this.ranks = ranks;
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

        public List<Rank> getRanks() {
            return ranks;
        }

        public void setRanks(List<Rank> ranks) {
            this.ranks = ranks;
        }

        /** Rank inner class */
        public static class Rank {

            private int pieces;
            private ArmorSetSkill skill;

            public Rank(int pieces, ArmorSetSkill skill) {
                this.pieces = pieces;
                this.skill = skill;
            }

            public int getPieces() {
                return pieces;
            }

            public void setPieces(int pieces) {
                this.pieces = pieces;
            }

            public ArmorSetSkill getSkill() {
                return skill;
            }

            public void setSkill(ArmorSetSkill skill) {
                this.skill = skill;
            }

            /** Armor Set Skill inner class */
            public static class ArmorSetSkill {

                private String description;
                private String skillName;

                public ArmorSetSkill(String description, String skillName) {
                    this.description = description;
                    this.skillName = skillName;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getSkillName() {
                    return skillName;
                }

                public void setSkillName(String skillName) {
                    this.skillName = skillName;
                }

            }

        }

    }

}
