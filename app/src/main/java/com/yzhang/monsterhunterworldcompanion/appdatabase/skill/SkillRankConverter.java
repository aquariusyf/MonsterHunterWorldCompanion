package com.yzhang.monsterhunterworldcompanion.appdatabase.skill;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class SkillRankConverter {

    @TypeConverter
    public static List<Skill.SkillRank> stringToList(String skillRankString) {
        List<Skill.SkillRank> skillRankList = new ArrayList<>();
        String[] skillRank = skillRankString.split("@");
        for(String rank: skillRank) {
            String[] singleSKillRank = rank.split("#");
            skillRankList.add(new Skill.SkillRank(
                    Integer.valueOf(singleSKillRank[0]), singleSKillRank[1]));
        }
        return skillRankList;
    }

    @TypeConverter
    public static String listToString(List<Skill.SkillRank> skillRankList) {
        String skillRankString = "";
        for(int i = 0; i < skillRankList.size(); i++) {
            if(i != 0) {
                skillRankString += "@";
                skillRankString += String.valueOf(skillRankList.get(i).getLevel());
                skillRankString += "#";
                skillRankString += skillRankList.get(i).getDescription();
            } else {
                skillRankString += String.valueOf(skillRankList.get(i).getLevel());
                skillRankString += "#";
                skillRankString += skillRankList.get(i).getDescription();
            }
        }
        return skillRankString;
    }

}
