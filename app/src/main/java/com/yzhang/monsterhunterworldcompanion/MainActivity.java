package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.yzhang.monsterhunterworldcompanion.apirequest.GetMonsters;
import com.yzhang.monsterhunterworldcompanion.apirequest.UrlUtils;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppExecutors;
import com.yzhang.monsterhunterworldcompanion.appdatabase.Monster;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String IS_FIRST_START_KEY = "is_first_start";

    //UI
    private FrameLayout mMonstersNavButton;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        if(isFirstStart()) {
            getMonsters();
        }

    }
    /** Life cycle end */

    /** Initiate views and set listener */
    private void initViews() {
        mMonstersNavButton = findViewById(R.id.nav_button_monsters);
        mMonstersNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonsterListActivity.class);
                startActivity(intent);
            }
        });
    }

    /** Check whether is initial app start */
    private boolean isFirstStart() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        if(sharedPreferences.contains(IS_FIRST_START_KEY)) {
            return false;
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_FIRST_START_KEY, false);
            editor.commit();
            return true;
        }
    }

    /** Get monsters data from api */
    private void getMonsters() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetMonsters request = retrofit.create(GetMonsters.class);
        Call<List<Monster>> call = request.getMonsterCall();
        call.enqueue(new Callback<List<Monster>>() {
            @Override
            public void onResponse(Call<List<Monster>> call, final Response<List<Monster>> response) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase db = AppDataBase.getInstance(MainActivity.this);
                        db.monsterDao().insertMonsters(response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Monster>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect" + UrlUtils.BASE_URL + UrlUtils.ALL_MONSTER_PATH);
            }
        });
    }

}
