package com.yzhang.monsterhunterworldcompanion.appdatabase.armorset;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArmorDetailDao {

    @Query("SELECT * FROM armorDetail ORDER BY armorSetId")
    List<ArmorDetail> getAll();

    @Query("SELECT * FROM armorDetail WHERE armorSetId = :id")
    ArmorDetail getArmorDetailById(int id);

    @Query("DELETE FROM armorDetail")
    void nukeTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArmorDetail(ArmorDetail newArmorDetail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArmorDetails(ArmorDetail... armorDetails);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArmorDetail(ArmorDetail armorDetail);

    @Delete
    void deleteArmorDetail(ArmorDetail armorDetail);

}
