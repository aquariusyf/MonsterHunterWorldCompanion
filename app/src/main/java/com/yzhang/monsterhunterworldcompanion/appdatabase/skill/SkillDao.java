package com.yzhang.monsterhunterworldcompanion.appdatabase.skill;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SkillDao {

    @Query("SELECT * FROM skill ORDER BY name")
    LiveData<List<Skill>> getAll();

    @Query("SELECT * FROM skill ORDER BY name")
    List<Skill> getAllSkill();

    @Query("SELECT * FROM skill WHERE id = :id")
    LiveData<Skill> getSkillById(int id);

    @Query("SELECT * FROM skill WHERE primaryId = :id")
    LiveData<Skill> getSkillByPrimaryId(int id);

    @Query("SELECT * FROM skill WHERE name = :name")
    Skill getSkillByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSkill(Skill newSkill);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSkills(Skill... skills);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSkill(Skill skill);

    @Delete
    void deleteSkill(Skill skill);

}
