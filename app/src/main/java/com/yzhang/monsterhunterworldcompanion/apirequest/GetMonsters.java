package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.Monster;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMonsters {

    @GET(UrlUtils.ALL_MONSTER_PATH)
    Call<List<Monster>> getMonsterCall();

}
