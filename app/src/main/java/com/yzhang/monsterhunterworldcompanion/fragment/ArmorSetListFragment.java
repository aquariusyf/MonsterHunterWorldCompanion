package com.yzhang.monsterhunterworldcompanion.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.yzhang.monsterhunterworldcompanion.R;

public class ArmorSetListFragment extends Fragment {

    private int mRankIndicator;
    private Context mContext;

    public ArmorSetListFragment(Context context, int rankIndicator) {
        mContext = context;
        mRankIndicator = rankIndicator;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_armorset_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
