package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzhang.monsterhunterworldcompanion.adapters.ArmorSetSkillListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppExecutors;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorDetail;
import com.yzhang.monsterhunterworldcompanion.fragment.ArmorSetListFragment;

import java.util.ArrayList;
import java.util.List;

public class ArmorSetDetailActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = ArmorSetDetailActivity.class.getSimpleName();

    //UI
    private TextView mArmorSetName;

    private List<ImageView> mHelmDecor = new ArrayList<>();
    private TextView mHelmName;
    private List<Pair<ImageView, TextView>> mHelmSkill = new ArrayList<>();

    private List<ImageView> mMailDecor = new ArrayList<>();
    private TextView mMailName;
    private List<Pair<ImageView, TextView>> mMailSkill = new ArrayList<>();

    private List<ImageView> mArmDecor = new ArrayList<>();
    private TextView mArmName;
    private List<Pair<ImageView, TextView>> mArmSkill = new ArrayList<>();

    private List<ImageView> mWaistDecor = new ArrayList<>();
    private TextView mWaistName;
    private List<Pair<ImageView, TextView>> mWaistSkill = new ArrayList<>();

    private List<ImageView> mLegDecor = new ArrayList<>();
    private TextView mLegName;
    private List<Pair<ImageView, TextView>> mLegSkill = new ArrayList<>();

    private RecyclerView mSkillSummaryListRv;
    private ArmorSetSkillListAdapter mAdapter;

    //val
    private int mArmorSetId;
    private ArmorDetail mArmorDetail;
    private List<String> mSkillNames;
    private List<Integer> mSkillLevels;


    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armor_set_detail);

        getArmorSetId();
        initViews();
        populateUI();
    }
    /** Life cycle end */

    /** Initialize views */
    private void initViews() {
        mArmorSetName = findViewById(R.id.tv_armor_set_detail_title);

        mHelmDecor.add((ImageView) findViewById(R.id.iv_helm_decor_1));
        mHelmDecor.add((ImageView) findViewById(R.id.iv_helm_decor_2));
        mHelmDecor.add((ImageView) findViewById(R.id.iv_helm_decor_3));
        mHelmName = findViewById(R.id.tv_helm_name);
        mHelmSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_helm_skill_1_icon),
                (TextView) findViewById(R.id.tv_helm_skill_1)));
        mHelmSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_helm_skill_2_icon),
                (TextView) findViewById(R.id.tv_helm_skill_2)));

        mMailDecor.add((ImageView) findViewById(R.id.iv_mail_decor_1));
        mMailDecor.add((ImageView) findViewById(R.id.iv_mail_decor_2));
        mMailDecor.add((ImageView) findViewById(R.id.iv_mail_decor_3));
        mMailName = findViewById(R.id.tv_mail_name);
        mMailSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_mail_skill_1_icon),
                (TextView) findViewById(R.id.tv_mail_skill_1)));
        mMailSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_mail_skill_2_icon),
                (TextView) findViewById(R.id.tv_mail_skill_2)));

        mArmDecor.add((ImageView) findViewById(R.id.iv_arm_decor_1));
        mArmDecor.add((ImageView) findViewById(R.id.iv_arm_decor_2));
        mArmDecor.add((ImageView) findViewById(R.id.iv_arm_decor_3));
        mArmName = findViewById(R.id.tv_arm_name);
        mArmSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_arm_skill_1_icon),
                (TextView) findViewById(R.id.tv_arm_skill_1)));
        mArmSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_arm_skill_2_icon),
                (TextView) findViewById(R.id.tv_arm_skill_2)));

        mWaistDecor.add((ImageView) findViewById(R.id.iv_waist_decor_1));
        mWaistDecor.add((ImageView) findViewById(R.id.iv_waist_decor_2));
        mWaistDecor.add((ImageView) findViewById(R.id.iv_waist_decor_3));
        mWaistName = findViewById(R.id.tv_waist_name);
        mWaistSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_waist_skill_1_icon),
                (TextView) findViewById(R.id.tv_waist_skill_1)));
        mWaistSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_waist_skill_2_icon),
                (TextView) findViewById(R.id.tv_waist_skill_2)));

        mLegDecor.add((ImageView) findViewById(R.id.iv_leg_decor_1));
        mLegDecor.add((ImageView) findViewById(R.id.iv_leg_decor_2));
        mLegDecor.add((ImageView) findViewById(R.id.iv_leg_decor_3));
        mLegName = findViewById(R.id.tv_leg_name);
        mLegSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_leg_skill_1_icon),
                (TextView) findViewById(R.id.tv_leg_skill_1)));
        mLegSkill.add(new Pair<>(
                (ImageView) findViewById(R.id.iv_leg_skill_2_icon),
                (TextView) findViewById(R.id.tv_leg_skill_2)));

        mSkillSummaryListRv = findViewById(R.id.rv_skill_summary);
        mSkillSummaryListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArmorSetSkillListAdapter(this, null, null);
        mSkillSummaryListRv.setAdapter(mAdapter);
    }

    /** Get and check armor set id from intent */
    private void getArmorSetId() {
        Intent intent = getIntent();
        if(intent == null) {
            finish();
        }
        if(!intent.hasExtra(ArmorSetListFragment.ARMOR_SET_ID_KEY)) {
            finish();
        }
        mArmorSetId = intent.getIntExtra(ArmorSetListFragment.ARMOR_SET_ID_KEY, -1);
        Log.v(LOG_TAG, "Armor Set ID: " + mArmorSetId);
    }

    /** Populate UI */
    private void populateUI() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDataBase db = AppDataBase.getInstance(ArmorSetDetailActivity.this);
                mArmorDetail = db.armorDetailDao().getArmorDetailById(mArmorSetId);

                /** Get skill summary and update adapter*/
                Pair<ArrayList<String>, ArrayList<Integer>> skillSummary =
                        ArmorDetail.getSkillSummary(mArmorDetail);
                mSkillNames = skillSummary.first;
                mSkillLevels = skillSummary.second;
                mAdapter.updateDataSet(mSkillNames, mSkillLevels);

                String armorSetName = mArmorDetail.getArmorSetName();
                mArmorSetName.setText(armorSetName);

                populateArmorPieceUI(
                        mArmorDetail.getHelmName(),
                        mHelmName,
                        mHelmDecor,
                        mHelmSkill,
                        mArmorDetail.getHelmSlotList(),
                        mArmorDetail.getHelmSkillList());

                populateArmorPieceUI(
                        mArmorDetail.getMailName(),
                        mMailName,
                        mMailDecor,
                        mMailSkill,
                        mArmorDetail.getMailSlotList(),
                        mArmorDetail.getMailSkillList());

                populateArmorPieceUI(
                        mArmorDetail.getArmName(),
                        mArmName,
                        mArmDecor,
                        mArmSkill,
                        mArmorDetail.getArmSlotList(),
                        mArmorDetail.getArmSkillList());

                populateArmorPieceUI(
                        mArmorDetail.getWaistName(),
                        mWaistName,
                        mWaistDecor,
                        mWaistSkill,
                        mArmorDetail.getWaistSlotList(),
                        mArmorDetail.getWaistSkillList());

                populateArmorPieceUI(
                        mArmorDetail.getLegName(),
                        mLegName,
                        mLegDecor,
                        mLegSkill,
                        mArmorDetail.getLegSlotList(),
                        mArmorDetail.getLegSkillList());
            }
        });
    }

    /** Populate armor piece UI */
    private void populateArmorPieceUI(
            String name,
            TextView nameTv,
            List<ImageView> decorIcon,
            List<Pair<ImageView, TextView>> skills,
            List<String> slotList,
            List<Pair<String, String>> skillList) {
        if(name == null || name.isEmpty()) {
            nameTv.setText("No Armor Piece");
            for(ImageView imageView: decorIcon) {
                imageView.setVisibility(View.INVISIBLE);
            }
            for(Pair<ImageView, TextView> pair: skills) {
                pair.first.setVisibility(View.INVISIBLE);
                pair.second.setVisibility(View.INVISIBLE);
            }
        } else {
            nameTv.setText(name);
            if(slotList != null && !slotList.isEmpty()) {
                int slotListIndex = 0;
                for(ImageView imageView: decorIcon) {
                    if(slotListIndex >= slotList.size()) {
                        imageView.setVisibility(View.INVISIBLE);
                    } else {
                        imageView.setImageResource(getSlotIcon(slotList.get(slotListIndex)));
                        slotListIndex++;
                    }
                }
            }
            if(skillList != null && !skillList.isEmpty()) {
                int skillListIndex = 0;
                for(Pair<ImageView, TextView> pair: skills) {
                    if(skillListIndex >= skillList.size()) {
                        pair.first.setVisibility(View.INVISIBLE);
                        pair.second.setVisibility(View.INVISIBLE);
                    } else {
                        //TODO: load skill icon;
                        pair.second.setText(skillList.get(skillListIndex).first
                                + " Lv." + skillList.get(skillListIndex).second);
                        skillListIndex++;
                    }
                }
            }
        }
    }

    /** Get the decor slot icon by level */
    private int getSlotIcon(String level) {
        int resourceId = getResources()
                .getIdentifier("decorlv" + level, "drawable", getPackageName());
        return resourceId;
    }

}