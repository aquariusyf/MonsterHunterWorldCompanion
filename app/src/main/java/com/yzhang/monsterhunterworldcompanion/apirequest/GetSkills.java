package com.yzhang.monsterhunterworldcompanion.apirequest;

import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetSkills {

    @GET(UrlUtils.ALL_SKILL_PATH)
    Call<List<Skill>> getSkillCall();

}
