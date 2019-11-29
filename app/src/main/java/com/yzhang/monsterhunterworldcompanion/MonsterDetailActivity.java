package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.Monster;

import java.util.List;

public class MonsterDetailActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = MonsterDetailActivity.class.getSimpleName();

    //UI
    private ImageView mLargeIcon;
    private ImageView mGuideImage;
    private TextView mNameTv;
    private TextView mSpeciesTv;
    private TextView mDescriptionTv;
    private TextView mLocationTv;
    private FloatingActionButton mBackBtn;

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

        mLargeIcon = findViewById(R.id.iv_monster_large_icon);
        Glide.with(this).load(getLargeIcon(monster.getId())).into(mLargeIcon);

        mGuideImage = findViewById(R.id.iv_monster_guide);
        Glide.with(this).load(getGuideImage(monster.getId())).into(mGuideImage);

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

        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /** Get the corresponding large icon of monster */
    private int getLargeIcon(int monsterId) {
        int resourceId = getResources().getIdentifier(
                "ml" + monsterId, "drawable", getPackageName());
        return resourceId;
    }

    /** Get the corresponding guide image of monster */
    private int getGuideImage(int monsterId) {
        int resourceId = getResources().getIdentifier(
                "mg" + monsterId, "drawable", getPackageName());
        return resourceId;
    }

}
