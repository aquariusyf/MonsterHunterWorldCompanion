package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;

public class ArmorSetListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDataBase mDb;
    private String mRank;

    public ArmorSetListViewModelFactory(AppDataBase db, String rank) {
        mDb = db;
        mRank = rank;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ArmorSetListViewModel(mDb, mRank);
    }

}
