package com.yzhang.monsterhunterworldcompanion.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;

import java.util.List;

public class ArmorSetListViewModel extends AndroidViewModel {

    private static final String LOG_TAG = ArmorSetListViewModel.class.getSimpleName();
    private LiveData<List<ArmorSet>> mArmorSets;

    public ArmorSetListViewModel(@NonNull Application application) {
        super(application);
        AppDataBase db = AppDataBase.getInstance(this.getApplication());
        Log.d(LOG_TAG, "Actively retrieving monster data from DB");
        mArmorSets = db.armorSetDao().getAll();
    }

    public LiveData<List<ArmorSet>> getArmorSets() {
        return this.mArmorSets;
    }

}
