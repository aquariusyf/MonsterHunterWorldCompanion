package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ArmorSetDetailActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = ArmorSetDetailActivity.class.getSimpleName();

    //UI
    private ImageView mHelmDecor1;
    private ImageView mHelmDecor2;
    private ImageView mHelmDecor3;
    private TextView mHelmName;
    private ImageView mHelmSkill1Icon;
    private TextView mHelmSkill1Name;
    private ImageView mHelmSkill2Icon;
    private TextView mHelmSkill2Name;

    private ImageView mMailDecor1;
    private ImageView mMailDecor2;
    private ImageView mMailDecor3;
    private TextView mMailName;
    private ImageView mMailSkill1Icon;
    private TextView mMailSkill1Name;
    private ImageView mMailSkill2Icon;
    private TextView mMailSkill2Name;

    private ImageView mArmDecor1;
    private ImageView mArmDecor2;
    private ImageView mArmDecor3;
    private TextView mArmName;
    private ImageView mArmSkill1Icon;
    private TextView mArmSkill1Name;
    private ImageView mArmSkill2Icon;
    private TextView mArmSkill2Name;

    private ImageView mWaistDecor1;
    private ImageView mWaistDecor2;
    private ImageView mWaistDecor3;
    private TextView mWaistName;
    private ImageView mWaistSkill1Icon;
    private TextView mWaistSkill1Name;
    private ImageView mWaistSkill2Icon;
    private TextView mWaistSkill2Name;

    private ImageView mLegDecor1;
    private ImageView mLegDecor2;
    private ImageView mLegDecor3;
    private TextView mLegName;
    private ImageView mLegSkill1Icon;
    private TextView mLegSkill1Name;
    private ImageView mLegSkill2Icon;
    private TextView mLegSkill2Name;




    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armor_set_detail);
    }
    /** Life cycle end */

    /** Initialize views */
    private void initViews() {
        mHelmDecor1 = findViewById(R.id.iv_helm_decor_1);
        mHelmDecor2 = findViewById(R.id.iv_helm_decor_2);
        mHelmDecor3 = findViewById(R.id.iv_helm_decor_3);
        mHelmName = findViewById(R.id.tv_helm_name);
        mHelmSkill1Icon = findViewById(R.id.iv_helm_skill_1_icon);
        mHelmSkill1Name = findViewById(R.id.tv_helm_skill_1);
        mHelmSkill2Icon = findViewById(R.id.iv_helm_skill_2_icon);
        mHelmSkill2Name = findViewById(R.id.tv_helm_skill_2);

        mMailDecor1 = findViewById(R.id.iv_mail_decor_1);
        mMailDecor2 = findViewById(R.id.iv_mail_decor_2);
        mMailDecor3 = findViewById(R.id.iv_mail_decor_3);
        mMailName = findViewById(R.id.tv_mail_name);
        mMailSkill1Icon = findViewById(R.id.iv_mail_skill_1_icon);
        mMailSkill1Name = findViewById(R.id.tv_mail_skill_1);
        mMailSkill2Icon = findViewById(R.id.iv_mail_skill_2_icon);
        mMailSkill2Name = findViewById(R.id.tv_mail_skill_2);

        mArmDecor1 = findViewById(R.id.iv_arm_decor_1);
        mArmDecor2 = findViewById(R.id.iv_arm_decor_2);
        mArmDecor3 = findViewById(R.id.iv_arm_decor_3);
        mArmName = findViewById(R.id.tv_arm_name);
        mArmSkill1Icon = findViewById(R.id.iv_arm_skill_1_icon);
        mArmSkill1Name = findViewById(R.id.tv_arm_skill_1);
        mArmSkill2Icon = findViewById(R.id.iv_arm_skill_2_icon);
        mArmSkill2Name = findViewById(R.id.tv_arm_skill_2);

        mWaistDecor1 = findViewById(R.id.iv_waist_decor_1);
        mWaistDecor2 = findViewById(R.id.iv_waist_decor_2);
        mWaistDecor3 = findViewById(R.id.iv_waist_decor_3);
        mWaistName = findViewById(R.id.tv_waist_name);
        mWaistSkill1Icon = findViewById(R.id.iv_waist_skill_1_icon);
        mWaistSkill1Name = findViewById(R.id.tv_waist_skill_1);
        mWaistSkill2Icon = findViewById(R.id.iv_waist_skill_2_icon);
        mWaistSkill2Name = findViewById(R.id.tv_waist_skill_2);

        mLegDecor1 = findViewById(R.id.iv_leg_decor_1);
        mLegDecor2 = findViewById(R.id.iv_leg_decor_2);
        mLegDecor3 = findViewById(R.id.iv_leg_decor_3);
        mLegName = findViewById(R.id.tv_leg_name);
        mLegSkill1Icon = findViewById(R.id.iv_leg_skill_1_icon);
        mLegSkill1Name = findViewById(R.id.tv_leg_skill_1);
        mLegSkill2Icon = findViewById(R.id.iv_leg_skill_2_icon);
        mLegSkill2Name = findViewById(R.id.tv_leg_skill_2);

    }

}
