package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.yzhang.monsterhunterworldcompanion.adapters.WeaponListPagerAdapter;
import com.yzhang.monsterhunterworldcompanion.fragment.SingleCategoryWeaponListFragment;
import com.yzhang.monsterhunterworldcompanion.fragment.WeaponCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class WeaponListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = WeaponListActivity.class.getSimpleName();

    //UI
    private static ViewPager mWeaponListViewPager;
    private  WeaponListPagerAdapter mAdapter;
    private  SingleCategoryWeaponListFragment mWeaponListFragment;

    //val
    private  List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_list);
        initViewPager();
    }

    /** Initiate view pager */
    private void initViewPager() {
        mWeaponListViewPager = findViewById(R.id.weapon_list_view_pager);

        mFragmentList = new ArrayList<>();
        WeaponCategoryFragment categoryFragment = new WeaponCategoryFragment();
        mFragmentList.add(categoryFragment);
        mWeaponListFragment = new SingleCategoryWeaponListFragment();
        mFragmentList.add(mWeaponListFragment);

        mAdapter = new WeaponListPagerAdapter(getSupportFragmentManager(), mFragmentList, this);
        mWeaponListViewPager.setAdapter(mAdapter);
    }

    /** Set view pager position to weapon list */
    public static void showWeaponList() {
        mWeaponListViewPager.setCurrentItem(1);
    }

}
