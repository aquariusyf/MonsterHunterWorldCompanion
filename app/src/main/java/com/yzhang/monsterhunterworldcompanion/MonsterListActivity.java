package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.monster.Monster;
import com.yzhang.monsterhunterworldcompanion.viewmodels.MonsterListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MonsterListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = MonsterListActivity.class.getSimpleName();
    public static final String MONSTER_KEY = "monster";
    public static final String MONSTER_BUNDLE_KEY = "monster_bundle";

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
        mAdapter = new MonsterListAdapter(
                this, new ArrayList<Monster>(), new MonsterListAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Monster monster = mAdapter.getMonster(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(MONSTER_KEY, monster);
                Intent intent = new Intent(MonsterListActivity.this, MonsterDetailActivity.class);
                intent.putExtra(MONSTER_BUNDLE_KEY, bundle);
                startActivity(intent);
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
                mAdapter.updateDataSet(monsterList);
            }
        });
    }

}
