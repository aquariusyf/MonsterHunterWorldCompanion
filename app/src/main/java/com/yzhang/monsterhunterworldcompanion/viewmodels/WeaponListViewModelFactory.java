package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;

public class WeaponListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDataBase mDb;
    private String mWeaponType;

    public WeaponListViewModelFactory(AppDataBase db, String weaponType) {
        this.mDb = db;
        this.mWeaponType = weaponType;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WeaponListViewModel(mDb, mWeaponType);
    }

}
