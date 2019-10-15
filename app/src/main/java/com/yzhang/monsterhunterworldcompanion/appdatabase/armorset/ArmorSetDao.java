package com.yzhang.monsterhunterworldcompanion.appdatabase.armorset;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArmorSetDao {

    @Query("SELECT * FROM armorSet ORDER BY name")
    LiveData<List<ArmorSet>> getAll();

    @Query("SELECT * FROM armorSet WHERE id = :id")
    LiveData<ArmorSet> getArmorSetById(int id);

    @Query("SELECT * FROM armorSet WHERE primaryId = :id")
    LiveData<ArmorSet> getArmorSetByPrimaryId(int id);

    @Query("SELECT * FROM armorSet WHERE rank = :rank ORDER BY name")
    LiveData<List<ArmorSet>> getArmorSetByRank(String rank);

    @Insert
    void insertArmorset(ArmorSet newArmorSet);

    @Insert
    void insertArmorSets(ArmorSet... armorSets);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArmorSet(ArmorSet armorSet);

    @Delete
    void deleteArmorSet(ArmorSet armorSet);

}
