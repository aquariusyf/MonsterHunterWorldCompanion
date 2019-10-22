package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yzhang.monsterhunterworldcompanion.apirequest.GetArmorSets;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetMonsters;
import com.yzhang.monsterhunterworldcompanion.apirequest.UrlUtils;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppExecutors;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorDetail;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSetMaster;
import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.Monster;

import java.util.ArrayList;
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
    private FrameLayout mArmorSetNavButton;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        if(isFirstStart()) {
            getMonsters();
            getArmorSets();
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

        mArmorSetNavButton = findViewById(R.id.nav_button_armor);
        mArmorSetNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArmorSetListActivity.class);
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

    /** Get monsters data using api and create monster table in database */
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
                        db.monsterDao().insertMonsters(response.body().toArray(new Monster[response.body().size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Monster>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect" + UrlUtils.BASE_URL + UrlUtils.ALL_MONSTER_PATH);
            }
        });
    }

    /** Get armor sets data using api and create armor set table in database */
    private void getArmorSets() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetArmorSets request = retrofit.create(GetArmorSets.class);
        Call<List<ArmorSetMaster>> call = request.getArmorSetCall();
        call.enqueue(new Callback<List<ArmorSetMaster>>() {
            @Override
            public void onResponse(Call<List<ArmorSetMaster>> call, Response<List<ArmorSetMaster>> response) {
                List<ArmorSetMaster> armorSetMasterList = response.body();
                if(armorSetMasterList == null || armorSetMasterList.isEmpty()) {
                    Log.v(LOG_TAG, "Armor set returned is empty!");
                    return;
                }
                final List<ArmorSet> armorSetList = new ArrayList<>();
                for(ArmorSetMaster armorSetMaster: armorSetMasterList) {
                    ArmorSetMaster.calculateTotalDefenceStats(armorSetMaster);
                    ArmorSet armorSet = new ArmorSet(
                            armorSetMaster.getId(),
                            armorSetMaster.getName(),
                            armorSetMaster.getRank(),
                            armorSetMaster.getTotalDefence(),
                            armorSetMaster.getThunderRes(),
                            armorSetMaster.getFireRes(),
                            armorSetMaster.getIceRes(),
                            armorSetMaster.getWaterRes(),
                            armorSetMaster.getDragonRes());
                    armorSetList.add(armorSet);
                }
                final List<ArmorDetail> armorDetailList = getArmorDetails(armorSetMasterList);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase db = AppDataBase.getInstance(MainActivity.this);
                        db.armorSetDao().insertArmorSets(armorSetList.toArray(new ArmorSet[armorSetList.size()]));
                        db.armorDetailDao().insertArmorDetails(armorDetailList.toArray(new ArmorDetail[armorDetailList.size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ArmorSetMaster>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect" + UrlUtils.BASE_URL + UrlUtils.ALL_ARMORSET_PATH);
            }
        });
    }

    /** Get list of ArmorDetails from armorSetMasterList */
    private List<ArmorDetail> getArmorDetails(List<ArmorSetMaster> armorSetMasterList) {
        List<ArmorDetail> armorDetailList = new ArrayList<>();
        for(ArmorSetMaster armorSetMaster: armorSetMasterList) {
            ArmorDetail armorDetail = new ArmorDetail(
                    armorSetMaster.getId(),
                    armorSetMaster.getName(),
                    ArmorSetMaster.getSetSkillName(armorSetMaster),
                    ArmorSetMaster.getSetSkills(armorSetMaster),
                    ArmorSetMaster.getSetSkillDescription(armorSetMaster),
                    ArmorSetMaster.getArmorPieceName(armorSetMaster, getString(R.string.armor_piece_helm)),
                    ArmorSetMaster.getArmorPieceSkills(armorSetMaster, getString(R.string.armor_piece_helm)),
                    ArmorSetMaster.getArmorPieceSlots(armorSetMaster, getString(R.string.armor_piece_helm)),
                    ArmorSetMaster.getArmorPieceName(armorSetMaster, getString(R.string.armor_piece_mail)),
                    ArmorSetMaster.getArmorPieceSkills(armorSetMaster, getString(R.string.armor_piece_mail)),
                    ArmorSetMaster.getArmorPieceSlots(armorSetMaster, getString(R.string.armor_piece_mail)),
                    ArmorSetMaster.getArmorPieceName(armorSetMaster, getString(R.string.armor_piece_arm)),
                    ArmorSetMaster.getArmorPieceSkills(armorSetMaster, getString(R.string.armor_piece_arm)),
                    ArmorSetMaster.getArmorPieceSlots(armorSetMaster, getString(R.string.armor_piece_arm)),
                    ArmorSetMaster.getArmorPieceName(armorSetMaster, getString(R.string.armor_piece_waist)),
                    ArmorSetMaster.getArmorPieceSkills(armorSetMaster, getString(R.string.armor_piece_waist)),
                    ArmorSetMaster.getArmorPieceSlots(armorSetMaster, getString(R.string.armor_piece_waist)),
                    ArmorSetMaster.getArmorPieceName(armorSetMaster, getString(R.string.armor_piece_leg)),
                    ArmorSetMaster.getArmorPieceSkills(armorSetMaster, getString(R.string.armor_piece_leg)),
                    ArmorSetMaster.getArmorPieceSlots(armorSetMaster, getString(R.string.armor_piece_leg)));
            armorDetailList.add(armorDetail);
        }
        return armorDetailList;
    }

}
