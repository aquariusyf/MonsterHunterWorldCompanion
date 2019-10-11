package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.ArmorSet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetArmorSets {

    @GET(UrlUtils.ALL_ARMORSET_PATH)
    Call<List<ArmorSet>> getArmorSetCall();

}
