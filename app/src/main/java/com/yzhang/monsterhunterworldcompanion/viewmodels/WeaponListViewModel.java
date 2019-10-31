package com.yzhang.monsterhunterworldcompanion.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.CommonMeleeWeapon;

import java.util.List;

public class WeaponListViewModel extends ViewModel {

    private LiveData<List<CommonMeleeWeapon>> mWeaponList;

    public WeaponListViewModel(AppDataBase db, String weaponType) {
        mWeaponList = db.commonMeleeWeaponDao().getAllByType(weaponType);
    }

    public LiveData<List<CommonMeleeWeapon>> getWeapons() {
        return this.mWeaponList;
    }

}
