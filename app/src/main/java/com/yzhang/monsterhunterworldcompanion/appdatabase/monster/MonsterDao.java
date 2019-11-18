package com.yzhang.monsterhunterworldcompanion.appdatabase.monster;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MonsterDao {

    @Query("SELECT * FROM monster ORDER BY id")
    LiveData<List<Monster>> getAll();

    @Query("SELECT * FROM monster WHERE id = :id")
    LiveData<Monster> getMonsterById(int id);

    @Query("SELECT * FROM monster WHERE idPrimary = :id")
    LiveData<Monster> getMonsterByPrimaryId(int id);

    @Query("SELECT * FROM monster WHERE name = :name")
    LiveData<Monster> getMonsterByName(String name);

    @Query("DELETE FROM monster")
    void nukeTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMonster(Monster newMonster);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMonsters(Monster... monsters);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMonster(Monster monster);

    @Delete
    void deleteMonster(Monster monster);

}
