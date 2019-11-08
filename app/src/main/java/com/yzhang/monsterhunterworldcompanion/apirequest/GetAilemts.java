package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.ailment.Ailment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAilemts {

    @GET(UrlUtils.ALL_AILMENT_PATH)
    Call<List<Ailment>> getAilmentCall();

}
