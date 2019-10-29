package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.CommonMeleeWeapon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getCommonMeleeWeapons {

    @GET(UrlUtils.ALL_WEAPON_PATH)
    Call<List<CommonMeleeWeapon>> getGreatSword(@Query("type") String greatSwordType);

}
