package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetWeapons {

    @GET(UrlUtils.ALL_WEAPON_PATH)
    Call<List<Weapon>> getWeaponCall();

}
