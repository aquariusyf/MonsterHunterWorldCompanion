package com.yzhang.monsterhunterworldcompanion.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.adapters.ArmorSetListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;
import com.yzhang.monsterhunterworldcompanion.viewmodels.ArmorSetListViewModel;
import com.yzhang.monsterhunterworldcompanion.viewmodels.ArmorSetListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ArmorSetListFragment extends Fragment {

    //const
    private static final String LOG_TAG = ArmorSetListFragment.class.getSimpleName();
    private static final String RANK_INDICATOR_KEY = "rank_indicator";
    private static final String MASTER_RANK = "master";
    private static final String HIGH_RANK = "high";
    private static final String LOW_RANK = "low";

    //UI
    private RecyclerView mArmorSetListRv;
    private ArmorSetListAdapter mAdapter;

    //val
    private AppDataBase mDb;

    public ArmorSetListFragment() {
        // Required empty constructor
    }

    /** Use newInstance method to pass arguments to fragment */
    public static ArmorSetListFragment newInstance(int rankIndicator) {
        Bundle args = new Bundle();
        args.putInt(RANK_INDICATOR_KEY, rankIndicator);
        ArmorSetListFragment f = new ArmorSetListFragment();
        f.setArguments(args);
        return f;
    }

    /** Life cycle begin */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_armorset_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDb = AppDataBase.getInstance(getContext());

        mArmorSetListRv = view.findViewById(R.id.rv_armorset_list);
        mArmorSetListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArmorSetListAdapter(
                getContext(),
                new ArrayList<ArmorSet>(),
                new MonsterListAdapter.OnListItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //TODO: Go to armor set details
                    }
                });
        mArmorSetListRv.setAdapter(mAdapter);
        setupViewModel();
    }
    /** Life cycle end */

    /** Initiate and setup view model */
    private void setupViewModel() {
        ArmorSetListViewModelFactory factory = null;
        switch (getArguments().getInt(RANK_INDICATOR_KEY)) {
            case 0:
                factory = new ArmorSetListViewModelFactory(mDb, MASTER_RANK);
                break;
            case 1:
                factory = new ArmorSetListViewModelFactory(mDb, HIGH_RANK);
                break;
            case 2:
                factory = new ArmorSetListViewModelFactory(mDb, LOW_RANK);
                break;
            default: break;
        }
        final ArmorSetListViewModel viewModel =
                ViewModelProviders.of(this, factory).get(ArmorSetListViewModel.class);
        viewModel.getArmorSets().observe(this, new Observer<List<ArmorSet>>() {
            @Override
            public void onChanged(List<ArmorSet> armorSets) {
                mAdapter.updateDataSet(armorSets);
            }
        });
    }

}