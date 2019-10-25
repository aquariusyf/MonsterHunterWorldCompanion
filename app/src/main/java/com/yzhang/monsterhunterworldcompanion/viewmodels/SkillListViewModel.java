package com.yzhang.monsterhunterworldcompanion.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;

import java.util.List;

public class SkillListViewModel extends AndroidViewModel {

    private static final String LOG_TAG = SkillListViewModel.class.getSimpleName();
    private LiveData<List<Skill>> mSkills;

    public SkillListViewModel(@NonNull Application application) {
        super(application);
        AppDataBase db = AppDataBase.getInstance(this.getApplication());
        Log.d(LOG_TAG, "Actively retrieving data from DB");
        mSkills = db.skillDao().getAll();
    }

    public LiveData<List<Skill>> getSkills() { return this.mSkills; }

}
