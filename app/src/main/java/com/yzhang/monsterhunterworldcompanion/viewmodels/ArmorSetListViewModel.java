package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;

import java.util.List;

public class ArmorSetListViewModel extends ViewModel {

    private LiveData<List<ArmorSet>> mArmorSets;

    public ArmorSetListViewModel(AppDataBase db, String rank) {
        mArmorSets = db.armorSetDao().getArmorSetByRank(rank);
    }

    public LiveData<List<ArmorSet>> getArmorSets() {
        return this.mArmorSets;
    }

}
