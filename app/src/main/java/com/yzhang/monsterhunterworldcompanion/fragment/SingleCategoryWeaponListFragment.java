package com.yzhang.monsterhunterworldcompanion.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.WeaponListActivity;
import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.WeaponListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;
import com.yzhang.monsterhunterworldcompanion.viewmodels.WeaponListViewModel;
import com.yzhang.monsterhunterworldcompanion.viewmodels.WeaponListViewModelFactory;

import java.util.List;

public class SingleCategoryWeaponListFragment extends Fragment {

    //const
    private static final String LOG_TAG = SingleCategoryWeaponListFragment.class.getSimpleName();
    public static final String ACTION_UPDATE_WEAPON_LIST =
            "com.yzhang.monsterhunterworldcompanion.updateWeaponList";

    //UI
    private static SingleCategoryWeaponListFragment mInstance;
    private TextView mWeaponCategoryName;
    private RecyclerView mWeaponListRv;
    private TextView mEmptyView;
    private WeaponListAdapter mAdapter;
    private WeaponListViewModelFactory mViewModelFactory;
    private WeaponListViewModel mViewModel;
    private AppDataBase mDb;
    private BroadcastReceiver mBroadcastReceiver;

    public SingleCategoryWeaponListFragment() {
        // Required empty constructor
    }

    /** Life cycle begin */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weapon_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mInstance = this;
        mDb = AppDataBase.getInstance(getContext());
        mWeaponCategoryName = view.findViewById(R.id.tv_category_name);

        mEmptyView = view.findViewById(R.id.empty_view);
        mWeaponListRv = view.findViewById(R.id.rv_weapon_list);
        mWeaponListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new WeaponListAdapter(getContext(), null, new MonsterListAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                populateWeaponDetail(mAdapter.getCurrentWeaponId(position));
                WeaponListActivity.showWeaponDetail();
            }
        });
        mWeaponListRv.setAdapter(mAdapter);
        createBroadcastReceiver();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
    /** Life cycle end */

    /** Initiate and setup view model */
    private void populateWeaponList(String weaponType, String weaponCategory) {
        mEmptyView.setVisibility(View.GONE);
        if(mViewModel != null && mViewModel.getWeapons().hasObservers()) {
            mViewModel.getWeapons().removeObservers(this);
        }
        mWeaponCategoryName.setText(weaponCategory);
        mViewModelFactory = new WeaponListViewModelFactory(mDb, weaponType);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(WeaponListViewModel.class);
        mViewModel.updateLiveData(mDb, weaponType);
        mViewModel.getWeapons().observe(this, new Observer<List<Weapon>>() {
            @Override
            public void onChanged(List<Weapon> weapons) {
                mAdapter.updateDataSet(weapons);
            }
        });
    }

    /** Create broadcast receiver to update weapon list */
    private void createBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(ACTION_UPDATE_WEAPON_LIST)) {
                    Bundle bundle = intent.getBundleExtra(WeaponCategoryFragment.CATEGORY_BUNDLE_KEY);
                    if(bundle == null) {
                        return;
                    }
                    if(bundle.containsKey(WeaponCategoryFragment.WEAPON_CATEGORY_KEY)
                            && bundle.containsKey(WeaponCategoryFragment.WEAPON_TYPE_KEY)) {
                        String category = bundle.getString(WeaponCategoryFragment.WEAPON_CATEGORY_KEY);
                        String type = bundle.getString(WeaponCategoryFragment.WEAPON_TYPE_KEY);
                        mInstance.populateWeaponList(type, category);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UPDATE_WEAPON_LIST);
        getActivity().registerReceiver(mBroadcastReceiver, filter);
    }

    /** Create and send broadcast to populate weapon detail */
    private void populateWeaponDetail(int weaponId) {
        Intent populateWeaponDetailIntent =
                new Intent(WeaponDetailFragment.ACTION_POPULATE_WEAPON_DETAIL);
        Bundle bundle = new Bundle();
        bundle.putInt(WeaponDetailFragment.WEAPON_ID_KEY, weaponId);
        populateWeaponDetailIntent.putExtra(WeaponDetailFragment.WEAPON_DETAIL_BUNDLE_KEY, bundle);
        getActivity().sendBroadcast(populateWeaponDetailIntent);
    }

}
