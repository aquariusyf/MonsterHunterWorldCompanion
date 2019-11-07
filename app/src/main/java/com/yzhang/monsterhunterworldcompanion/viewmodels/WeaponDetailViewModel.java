package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;


public class WeaponDetailViewModel extends ViewModel {

    private LiveData<Weapon> mWeapon;

    public WeaponDetailViewModel(AppDataBase db, int weaponId) {
        mWeapon = db.weaponDao().getWeaponById(weaponId);
    }

    public void updateLiveData(AppDataBase db, int weaponId) {
        mWeapon = db.weaponDao().getWeaponById(weaponId);
    }

    public LiveData<Weapon> getWeapon() {
        return this.mWeapon;
    }

}
