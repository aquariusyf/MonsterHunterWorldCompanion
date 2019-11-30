package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yzhang.monsterhunterworldcompanion.fragment.SingleCategoryWeaponListFragment;
import com.yzhang.monsterhunterworldcompanion.fragment.WeaponCategoryFragment;
import com.yzhang.monsterhunterworldcompanion.fragment.WeaponDetailFragment;

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

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        switch (position) {
            case 0:
                WeaponCategoryFragment categoryFragment =
                        (WeaponCategoryFragment) super.instantiateItem(container, position);
                mFragmentList.set(position, categoryFragment);
                return categoryFragment;
            case 1:
                SingleCategoryWeaponListFragment weaponListFragment =
                        (SingleCategoryWeaponListFragment) super.instantiateItem(container, position);
                mFragmentList.set(position, weaponListFragment);
                return weaponListFragment;
            case 2:
                WeaponDetailFragment detailFragment =
                        (WeaponDetailFragment) super.instantiateItem(container, position);
                mFragmentList.set(position, detailFragment);
                return detailFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
