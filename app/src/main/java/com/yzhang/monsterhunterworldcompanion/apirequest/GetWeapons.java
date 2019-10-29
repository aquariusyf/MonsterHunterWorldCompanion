package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.CommonMeleeWeapon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetWeapons {

    @GET(UrlUtils.ALL_GREAT_SWORD_PATH)
    Call<List<CommonMeleeWeapon>> getGreatSwordCall();

}
