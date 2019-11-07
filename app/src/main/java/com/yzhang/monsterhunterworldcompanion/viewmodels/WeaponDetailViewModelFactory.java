package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;

public class WeaponDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDataBase mDb;
    private int mWeaponId;

    public WeaponDetailViewModelFactory(AppDataBase db, int weaponId) {
        this.mDb = db;
        this.mWeaponId = weaponId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WeaponDetailViewModel(mDb, mWeaponId);
    }

}
