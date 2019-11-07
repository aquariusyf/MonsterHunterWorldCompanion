package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;

import java.util.List;

public class WeaponListViewModel extends ViewModel {

    private LiveData<List<Weapon>> mWeaponList;

    public WeaponListViewModel(AppDataBase db, String weaponType) {
        mWeaponList = db.weaponDao().getAllByType(weaponType);
    }

    public void updateLiveData(AppDataBase db, String weaponType) {
        mWeaponList = db.weaponDao().getAllByType(weaponType);
    }

    public LiveData<List<Weapon>> getWeapons() {
        return this.mWeaponList;
    }

}
