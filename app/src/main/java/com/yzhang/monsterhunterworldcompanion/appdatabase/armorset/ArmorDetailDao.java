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
public interface ArmorDetailDao {

    @Query("SELECT * FROM armorDetail ORDER BY armorSetId")
    LiveData<List<ArmorDetail>> getAll();

    @Query("SELECT * FROM armorDetail WHERE armorSetId = :id")
    LiveData<ArmorDetail> getArmorDetailById(int id);

    @Insert
    void insertArmorDetail(ArmorDetail newArmorDetail);

    @Insert
    void insertArmorDetails(ArmorDetail... armorDetails);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArmorDetail(ArmorDetail armorDetail);

    @Delete
    void deleteArmorDetail(ArmorDetail armorDetail);

}
