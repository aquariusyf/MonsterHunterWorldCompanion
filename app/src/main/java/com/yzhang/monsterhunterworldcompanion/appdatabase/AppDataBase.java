package com.yzhang.monsterhunterworldcompanion.appdatabase;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.LocationConverter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.Monster;
import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.MonsterDao;

@Database(entities = {Monster.class}, version = 1, exportSchema = false)
@TypeConverters(LocationConverter.class)
public abstract class AppDataBase extends RoomDatabase {

    private static final String LOG_TAG = AppDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "app_database";
    private static AppDataBase sInstance;

    public static AppDataBase getInstance(Context context) {
        if(sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class, AppDataBase.DATABASE_NAME).build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract MonsterDao monsterDao();

}
