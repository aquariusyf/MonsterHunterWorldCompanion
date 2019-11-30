package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.fragment.ArmorSetListFragment;

import java.util.List;

public class ArmorSetPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList; // The fragment list
    private Context mContext;

    public ArmorSetPagerAdapter(
            @NonNull FragmentManager fm,
            List<Fragment> fragmentList,
            Context context) {
        super(fm);
        mFragmentList = fragmentList;
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList == null ? null : mFragmentList.get(position);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ArmorSetListFragment fragmentInstance =
                (ArmorSetListFragment) super.instantiateItem(container, position);
        mFragmentList.set(position, fragmentInstance);
        return fragmentInstance;
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.rank_indicator_master);
            case 1:
                return mContext.getString(R.string.rank_indicator_high);
            case 2:
                return mContext.getString(R.string.rank_indicator_low);
            default:
                return null;
        }
    }

}
