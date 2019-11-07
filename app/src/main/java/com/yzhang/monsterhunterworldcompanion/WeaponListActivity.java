package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.yzhang.monsterhunterworldcompanion.adapters.WeaponListPagerAdapter;
import com.yzhang.monsterhunterworldcompanion.fragment.SingleCategoryWeaponListFragment;
import com.yzhang.monsterhunterworldcompanion.fragment.WeaponCategoryFragment;
import com.yzhang.monsterhunterworldcompanion.fragment.WeaponDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class WeaponListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = WeaponListActivity.class.getSimpleName();

    //UI
    private static ViewPager mWeaponListViewPager;
    private  WeaponListPagerAdapter mAdapter;
    private WeaponCategoryFragment mCategoryFragment;
    private  SingleCategoryWeaponListFragment mWeaponListFragment;
    private WeaponDetailFragment mWeaponDetailFragment;

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
        mCategoryFragment = new WeaponCategoryFragment();
        mFragmentList.add(mCategoryFragment);
        mWeaponListFragment = new SingleCategoryWeaponListFragment();
        mFragmentList.add(mWeaponListFragment);
        mWeaponDetailFragment = new WeaponDetailFragment();
        mFragmentList.add(mWeaponDetailFragment);

        mWeaponListViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);

        mAdapter = new WeaponListPagerAdapter(getSupportFragmentManager(), mFragmentList, this);
        mWeaponListViewPager.setAdapter(mAdapter);
    }

    /** Set view pager position to weapon list */
    public static void showWeaponList() {
        mWeaponListViewPager.setCurrentItem(1);
    }

    /** Set view pager position to weapon detail */
    public static void showWeaponDetail() {
        mWeaponListViewPager.setCurrentItem(2);
    }

}
