package com.yzhang.monsterhunterworldcompanion.fragment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.WeaponListActivity;
import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.WeaponCategoryAdapter;

public class WeaponCategoryFragment extends Fragment {

    //const
    private static final String LOG_TAG = WeaponCategoryFragment.class.getSimpleName();
    public static final String CATEGORY_BUNDLE_KEY = "category-bundle";
    public static final String WEAPON_TYPE_KEY = "weapon-type";
    public static final String WEAPON_CATEGORY_KEY = "weapon-category";

    //UI
    private RecyclerView mCategoryGrid;
    private WeaponCategoryAdapter mAdapter;

    public WeaponCategoryFragment() {
        // Required empty constructor
    }

    /** Life cycle begin */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weapon_category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mCategoryGrid = view.findViewById(R.id.rv_weapon_category_grid);
        mCategoryGrid.setLayoutManager(new GridLayoutManager(
                getContext(), numberOfColumns(getActivity()), RecyclerView.VERTICAL, false));
        mAdapter = new WeaponCategoryAdapter(getContext(), new MonsterListAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                updateWeaponList(mAdapter.getWeaponCategory(position));
                WeaponListActivity.showWeaponList();
            }
        });
        mCategoryGrid.setAdapter(mAdapter);
    }
    /** Life cycle end */

    /** Calculate number of columns to keep grid aspect */
    private int numberOfColumns(Activity aActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        aActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 750;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    /** Create and send intent to update weapon list */
    private void updateWeaponList(Pair<String, String> category) {
        Intent updateWeaponIntent =
                new Intent(SingleCategoryWeaponListFragment.ACTION_UPDATE_WEAPON_LIST);
        Bundle bundle = new Bundle();
        bundle.putString(WEAPON_CATEGORY_KEY, category.first);
        bundle.putString(WEAPON_TYPE_KEY, category.second);
        updateWeaponIntent.putExtra(CATEGORY_BUNDLE_KEY, bundle);
        getActivity().sendBroadcast(updateWeaponIntent);
//        PendingIntent update = PendingIntent.getBroadcast(
//                getContext(),
//                0,
//                updateWeaponIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        try {
//            update.send();
//            Log.v(LOG_TAG, "update pending intent sent!");
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }
    }

}
