package com.yzhang.monsterhunterworldcompanion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.yzhang.monsterhunterworldcompanion.R;

public class WeaponDetailFragment extends Fragment {

    //const
    private static final String LOG_TAG = WeaponDetailFragment.class.getSimpleName();

    public WeaponDetailFragment() {
        // Required empty constructor
    }

    /** Life cycle begin */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weapon_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    /** Life cycle end */

}
