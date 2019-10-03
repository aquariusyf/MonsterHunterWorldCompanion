package com.yzhang.monsterhunterworldcompanion.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.Monster;

import java.util.List;

public class MonsterListViewModel extends AndroidViewModel {

    private static final String LOG_TAG = MonsterListViewModel.class.getSimpleName();
    private LiveData<List<Monster>> mMonsters;

    public MonsterListViewModel(@NonNull Application application) {
        super(application);
        AppDataBase db = AppDataBase.getInstance(this.getApplication());
        Log.d(LOG_TAG, "Actively retrieving monster data from DB");
        mMonsters = db.monsterDao().getAll();
    }

    public LiveData<List<Monster>> getMonsters() {
        return this.mMonsters;
    }

}
