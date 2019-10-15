package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.yzhang.monsterhunterworldcompanion.adapters.ArmorSetPagerAdapter;
import com.yzhang.monsterhunterworldcompanion.fragment.ArmorSetListFragment;

import java.util.ArrayList;
import java.util.List;

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
        mViewPager = findViewById(R.id.view_pager);
        mTab = findViewById(R.id.pager_tab);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(ArmorSetListFragment.newInstance(0));
        fragmentList.add(ArmorSetListFragment.newInstance(1));
        fragmentList.add(ArmorSetListFragment.newInstance(2));

        mViewPager.setAdapter(
                new ArmorSetPagerAdapter(getSupportFragmentManager(), fragmentList, this));
        mTab.setupWithViewPager(mViewPager);
    }

}
