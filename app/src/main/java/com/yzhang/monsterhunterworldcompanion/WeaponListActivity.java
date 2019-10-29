package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class WeaponListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = WeaponListActivity.class.getSimpleName();

    //UI
    private ViewPager mWeaponListViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_list);
        initViewPager();
    }

    /** Initiate view pager */
    private void initViewPager() {

    }

}
