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
public interface WeaponDao {

    @Query("SELECT * FROM weapon ORDER BY name")
    LiveData<List<Weapon>> getAll();

    @Query("SELECT * FROM weapon WHERE type = :type ORDER BY id")
    LiveData<List<Weapon>> getAllByType(String type);

    @Query("SELECT * FROM weapon WHERE id = :id")
    LiveData<Weapon> getWeaponById(int id);

    @Query("SELECT * FROM weapon WHERE primaryId = :id")
    LiveData<Weapon> getWeaponByPrimaryId(int id);

    @Query("SELECT * FROM weapon WHERE name = :name")
    Weapon getWeaponByName(String name);

    @Query("DELETE FROM weapon")
    void nukeTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeapon(Weapon newWeapon);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeapons(Weapon... weapons);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWeapon(Weapon weapon);

    @Delete
    void deleteWeapon(Weapon weapon);

}
