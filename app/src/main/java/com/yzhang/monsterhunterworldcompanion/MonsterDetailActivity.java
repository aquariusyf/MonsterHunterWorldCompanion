package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yzhang.monsterhunterworldcompanion.appdatabase.Monster;

import java.util.List;

public class MonsterDetailActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = MonsterDetailActivity.class.getSimpleName();

    //UI
    private ImageView mIconIv;
    private TextView mNameTv;
    private TextView mSpeciesTv;
    private TextView mDescriptionTv;
    private TextView mLocationTv;

    //val
    private Bundle monsterBundle = new Bundle();

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_detail);

        getBundle();
        initViews();
    }
    /** Life cycle end */

    /** Check and get bundle from intent */
    private void getBundle() {
        Intent intent = getIntent();
        if(intent == null) {
            Log.e(LOG_TAG, "Intent is null!");
            finish();
        }
        monsterBundle = intent.getBundleExtra(MonsterListActivity.MONSTER_BUNDLE_KEY);
        if(monsterBundle == null) {
            Log.e(LOG_TAG, "Bundle is null!");
            finish();
        }
    }

    /** Initiate and populate views */
    private void initViews() {
        Monster monster = monsterBundle.getParcelable(MonsterListActivity.MONSTER_KEY);

        mIconIv = findViewById(R.id.iv_monster_icon);
        Glide.with(this).load(monster.getIcon()).into(mIconIv);

        mNameTv = findViewById(R.id.tv_monster_name);
        mNameTv.setText(monster.getName());

        mSpeciesTv = findViewById(R.id.tv_monster_species);
        mSpeciesTv.setText(monster.getSpecies());

        mDescriptionTv = findViewById(R.id.tv_monster_description);
        mDescriptionTv.setText(monster.getDescription());

        mLocationTv = findViewById(R.id.tv_monster_locations);
        List<Monster.Location> locations = monster.getLocations();
        for(int i = 0; i < locations.size(); i++) {
            mLocationTv.append(locations.get(i).getName() + "\n");
        }
    }

}
