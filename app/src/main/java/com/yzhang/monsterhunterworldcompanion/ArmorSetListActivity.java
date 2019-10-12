package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ArmorSetListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = ArmorSetListActivity.class.getSimpleName();

    //UI
    private ViewPager mViewPager;
    private TabLayout mTab;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armorset_list);

        initViewPager();
    }
    /** Life cycle end */

    /** Initialize view pager */
    private void initViewPager() {

    }

}
