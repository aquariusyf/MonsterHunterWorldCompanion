package com.yzhang.monsterhunterworldcompanion.appdatabase.Ailment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AilmentDao {

    @Query("SELECT * FROM ailment ORDER BY name")
    LiveData<List<Ailment>> getAll();

    @Query("SELECT * FROM ailment WHERE id = :id")
    LiveData<Ailment> getAimentById(int id);

    @Query("SELECT * FROM ailment WHERE primaryId = :id")
    LiveData<Ailment> getAilmentByPrimaryId(int id);

    @Query("SELECT * FROM ailment WHERE name = :name")
    Ailment getAilmentByName(String name);

    @Insert
    void insertAilment(Ailment newAilment);

    @Insert
    void insertAilments(Ailment... ailments);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAilment(Ailment ailment);

    @Delete
    void deleteAilment(Ailment ailment);

}
