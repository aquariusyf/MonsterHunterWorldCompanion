package com.yzhang.monsterhunterworldcompanion.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.ailment.Ailment;

import java.util.List;

public class AilmentListViewModel extends AndroidViewModel {

    private static final String LOG_TAG = AilmentListViewModel.class.getSimpleName();
    private LiveData<List<Ailment>> mAilments;

    public AilmentListViewModel(@NonNull Application application) {
        super(application);
        AppDataBase db = AppDataBase.getInstance(this.getApplication());
        Log.d(LOG_TAG, "Actively retrieving data from DB");
        mAilments = db.ailmentDao().getAll();
    }

    public LiveData<List<Ailment>> getAilments() {
        return this.mAilments;
    }

}
