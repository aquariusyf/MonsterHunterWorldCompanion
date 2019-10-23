package com.yzhang.monsterhunterworldcompanion.appdatabase.skill;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "skill")
public class Skill {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "ranks")
    private List<SkillRank> ranks;

    public Skill(
            int primaryId,
            int id,
            String name,
            String description,
            List<SkillRank> ranks) {
        this.primaryId = primaryId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.ranks = ranks;
    }

    @Ignore
    public Skill(int id, String name, String description, List<SkillRank> ranks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ranks = ranks;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkillRank> getRanks() {
        return ranks;
    }

    public void setRanks(List<SkillRank> ranks) {
        this.ranks = ranks;
    }

    public static class SkillRank {

        private int level;
        private String description;

        public SkillRank(int level, String description) {
            this.level = level;
            this.description = description;
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
    }

}
