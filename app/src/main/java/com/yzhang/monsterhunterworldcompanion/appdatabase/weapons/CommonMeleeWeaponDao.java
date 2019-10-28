package com.yzhang.monsterhunterworldcompanion.appdatabase.weapons;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommonMeleeWeaponDao {

    @Query("SELECT * FROM commonMeleeWeapon ORDER BY name")
    LiveData<List<CommonMeleeWeapon>> getAll();

    @Query("SELECT * FROM commonMeleeWeapon WHERE type = :type ORDER BY id")
    LiveData<List<CommonMeleeWeapon>> getAllByType(String type);

    @Query("SELECT * FROM commonMeleeWeapon WHERE id = :id")
    LiveData<CommonMeleeWeapon> getWeaponById(int id);

    @Query("SELECT * FROM commonMeleeWeapon WHERE primaryId = :id")
    LiveData<CommonMeleeWeapon> getWeaponByPrimaryId(int id);

    @Query("SELECT * FROM commonMeleeWeapon WHERE name = :name")
    CommonMeleeWeapon getWeaponByName(String name);

    @Insert
    void insertWeapon(CommonMeleeWeapon newWeapon);

    @Insert
    void insertWeapons(CommonMeleeWeapon... weapons);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWeapon(CommonMeleeWeapon weapon);

    @Delete
    void deleteWeapon(CommonMeleeWeapon weapon);

}
