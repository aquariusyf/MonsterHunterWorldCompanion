package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class WeaponListPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private Context mContext;

    public WeaponListPagerAdapter(
            @NonNull FragmentManager fm,
            List<Fragment> fragmentList,
            Context context) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList == null ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
