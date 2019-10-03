package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.Monster;
import com.yzhang.monsterhunterworldcompanion.viewmodels.MonsterListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MonsterListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = MonsterListActivity.class.getSimpleName();

    //UI
    private RecyclerView mMonsterListRv;
    private MonsterListAdapter mAdapter;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_list);

        initViews();
        setupViewModel();

    }
    /** Life cycle end */

    /** Initiate views and set listener */
    private void initViews() {
        mMonsterListRv = findViewById(R.id.rv_monster_list);
        mMonsterListRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mMonsterListRv.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new MonsterListAdapter(
                this, new ArrayList<Monster>(), new MonsterListAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //TODO: go to monster details
            }
        });
        mMonsterListRv.setAdapter(mAdapter);
    }

    /** Initiate and setup view model */
    private void setupViewModel() {
        MonsterListViewModel viewModel = ViewModelProviders
                .of(this)
                .get(MonsterListViewModel.class);
        viewModel.getMonsters().observe(this, new Observer<List<Monster>>() {
            @Override
            public void onChanged(List<Monster> monsterList) {
                Log.v(LOG_TAG, "onChanged Called!!!!");
                mAdapter.updateDataSet(monsterList);
            }
        });
    }

}
