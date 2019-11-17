package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.events.EventQuest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetEvents {

    @GET(UrlUtils.ALL_EVENT_PATH)
    Call<List<EventQuest>> getEventCall();

}
