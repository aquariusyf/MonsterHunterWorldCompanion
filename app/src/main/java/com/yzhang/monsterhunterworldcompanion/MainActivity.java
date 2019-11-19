package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;
import com.yzhang.monsterhunterworldcompanion.adapters.EventListAdapter;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetAilemts;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetArmorSets;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetEvents;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetWeapons;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetMonsters;
import com.yzhang.monsterhunterworldcompanion.apirequest.GetSkills;
import com.yzhang.monsterhunterworldcompanion.apirequest.UrlUtils;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppExecutors;
import com.yzhang.monsterhunterworldcompanion.appdatabase.ailment.Ailment;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorDetail;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSetMaster;
import com.yzhang.monsterhunterworldcompanion.appdatabase.events.EventQuest;
import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.Monster;
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;

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
    private static final int ABOUT_DIALOG_MARGIN_TOP = 120;
    private static final int ABOUT_DIALOG_MARGIN_LEFT = 48;
    private static final int ABOUT_DIALOG_MARGIN_RIGHT = 48;
    private static final int ABOUT_DIALOG_MARGIN_BOTTOM = 240;
    private static final int ABOUT_DIALOG_PADDING = 20;

    //UI
    private TextView mNetworkIndicatorTv;
    private ScrollView mMenuView;
    private FrameLayout mEventsNavButton;
    private FrameLayout mMonstersNavButton;
    private FrameLayout mSkillsNavButton;
    private FrameLayout mArmorSetNavButton;
    private FrameLayout mWeaponNavButton;
    private FrameLayout mAilmentNavButton;
    private ImageView mOptionsMenu;
    private DialogPlus mDialogPlus;
    private DialogPlusBuilder mDialogPlusBuilder;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        if(isFirstStart()) {
            if(isNetworkAvailable()) {
                getMonsters();
                getArmorSets();
                getSkills();
                getWeapons();
                getAilments();
                mMenuView.setVisibility(View.VISIBLE);
                mNetworkIndicatorTv.setVisibility(View.GONE);
            } else {
                mMenuView.setVisibility(View.GONE);
                mNetworkIndicatorTv.setVisibility(View.VISIBLE);
            }
        }
    }
    /** Life cycle end */

    /** Check network connectivity */
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    /** Initiate views and set listener */
    private void initViews() {
        mNetworkIndicatorTv = findViewById(R.id.tv_connectivity_indicator);
        mMenuView = findViewById(R.id.menu_section);

        mEventsNavButton = findViewById(R.id.nav_button_events);
        mEventsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventListActivity.class);
                startActivity(intent);
            }
        });

        mMonstersNavButton = findViewById(R.id.nav_button_monsters);
        mMonstersNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonsterListActivity.class);
                startActivity(intent);
            }
        });

        mSkillsNavButton = findViewById(R.id.nav_button_skills);
        mSkillsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SkillListActivity.class);
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

        mWeaponNavButton = findViewById(R.id.nav_button_weapon);
        mWeaponNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeaponListActivity.class);
                startActivity(intent);
            }
        });

        mAilmentNavButton = findViewById(R.id.nav_button_ailments);
        mAilmentNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AilmentListActivity.class);
                startActivity(intent);
            }
        });

        mOptionsMenu = findViewById(R.id.options_menu);
        mOptionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsMenu(v);
            }
        });

        createDialogPlusBuilder();
    }

    /** Show popup options menu */
    private void showOptionsMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.main_screen_popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Sync Game Data":
                        syncGameData();
                        break;
                    case "About":
                        showAboutDialog();
                        break;
                    default: break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    /** Create about dialog */
    private void createDialogPlusBuilder() {
        mDialogPlusBuilder = DialogPlus.newDialog(this)
                .setHeader(R.layout.dialog_header)
                .setContentHolder(new ViewHolder(R.layout.dialog_content))
                .setContentBackgroundResource(R.color.colorDialogHeaderBackground)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setMargin(
                        ABOUT_DIALOG_MARGIN_LEFT,
                        ABOUT_DIALOG_MARGIN_TOP,
                        ABOUT_DIALOG_MARGIN_RIGHT,
                        ABOUT_DIALOG_MARGIN_BOTTOM)
                .setPadding(ABOUT_DIALOG_PADDING, ABOUT_DIALOG_PADDING, ABOUT_DIALOG_PADDING, ABOUT_DIALOG_PADDING)
                .setInAnimation(R.anim.fade_in_center)
                .setOutAnimation(R.anim.fade_out_center)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setContentHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mDialogPlus = mDialogPlusBuilder.create();
    }

    /** Show about dialog */
    private void showAboutDialog() {
        mDialogPlus.show();
    }

    /** Check whether is initial app start */
    private boolean isFirstStart() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        if(sharedPreferences.contains(IS_FIRST_START_KEY)) {
            return false;
        } else {
            if(isNetworkAvailable()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(IS_FIRST_START_KEY, false);
                editor.commit();
                return true;
            } else {
                return true;
            }
        }
    }

    /** Sync game data using api */
    private void syncGameData() {
        if(isNetworkAvailable()) {
            getMonsters();
            getArmorSets();
            getSkills();
            getWeapons();
            getAilments();
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_FIRST_START_KEY, false);
            editor.commit();
            mMenuView.setVisibility(View.VISIBLE);
            mNetworkIndicatorTv.setVisibility(View.GONE);
            Toast.makeText(
                    MainActivity.this,
                    getString(R.string.sync_database_success_toast_text),
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    MainActivity.this,
                    getString(R.string.sync_database_fail_toast_text),
                    Toast.LENGTH_LONG).show();
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
                        db.monsterDao().nukeTable();
                        db.monsterDao().insertMonsters(response.body().toArray(new Monster[response.body().size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Monster>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect " + UrlUtils.BASE_URL + UrlUtils.ALL_MONSTER_PATH);
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
                            armorSetMaster.getPieces() == null || armorSetMaster.getPieces().isEmpty() ? 1 : armorSetMaster.getPieces().get(0).getRarity(),
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
                        db.armorSetDao().nukeTable();
                        db.armorSetDao().insertArmorSets(armorSetList.toArray(new ArmorSet[armorSetList.size()]));
                        db.armorDetailDao().nukeTable();
                        db.armorDetailDao().insertArmorDetails(armorDetailList.toArray(new ArmorDetail[armorDetailList.size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ArmorSetMaster>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect " + UrlUtils.BASE_URL + UrlUtils.ALL_ARMORSET_PATH);
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
                    armorSetMaster.getBonus() == null ? -1 : armorSetMaster.getBonus().getId(),
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

    /** Get skills data using api and create skill table in database */
    private void getSkills() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSkills request = retrofit.create(GetSkills.class);
        Call<List<Skill>> call = request.getSkillCall();
        call.enqueue(new Callback<List<Skill>>() {
            @Override
            public void onResponse(Call<List<Skill>> call, final Response<List<Skill>> response) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase db = AppDataBase.getInstance(MainActivity.this);
                        db.skillDao().nukeTable();
                        db.skillDao().insertSkills(response.body().toArray(new Skill[response.body().size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Skill>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect " + UrlUtils.BASE_URL + UrlUtils.ALL_SKILL_PATH);
            }
        });
    }

    /** Get weapon data using api and create table in database */
    private void getWeapons() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetWeapons request = retrofit.create(GetWeapons.class);
        Call<List<Weapon>> weaponCall = request.getWeaponCall();
        weaponCall.enqueue(new Callback<List<Weapon>>() {
            @Override
            public void onResponse(Call<List<Weapon>> call, final Response<List<Weapon>> response) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase db = AppDataBase.getInstance(MainActivity.this);
                        db.weaponDao().insertWeapons(response.body()
                                .toArray(new Weapon[response.body().size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Weapon>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect " + UrlUtils.BASE_URL + UrlUtils.ALL_WEAPON_PATH);
            }
        });
    }

    /** Get ailment data using api and create table in database */
    private void getAilments() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAilemts request = retrofit.create(GetAilemts.class);
        Call<List<Ailment>> ailmentCall = request.getAilmentCall();
        ailmentCall.enqueue(new Callback<List<Ailment>>() {
            @Override
            public void onResponse(Call<List<Ailment>> call, final Response<List<Ailment>> response) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDataBase db = AppDataBase.getInstance(MainActivity.this);
                        db.ailmentDao().nukeTable();
                        db.ailmentDao().insertAilments(response.body()
                                .toArray(new Ailment[response.body().size()]));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Ailment>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect " + UrlUtils.BASE_URL + UrlUtils.ALL_AILMENT_PATH);
            }
        });
    }

    /** Get event data using api */
    public static void getEvents(final EventListAdapter adapter, final LinearLayout loadingIndicator) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final GetEvents request = retrofit.create(GetEvents.class);
        Call<List<EventQuest>> eventCall = request.getEventCall();
        eventCall.enqueue(new Callback<List<EventQuest>>() {
            @Override
            public void onResponse(Call<List<EventQuest>> call, Response<List<EventQuest>> response) {
                if(response.body() != null) {
                    adapter.updateDataSet(response.body());
                    loadingIndicator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<EventQuest>> call, Throwable t) {
                Log.e(LOG_TAG, "Failed to connect " + UrlUtils.BASE_URL + UrlUtils.ALL_EVENT_PATH);
            }
        });
    }

}
