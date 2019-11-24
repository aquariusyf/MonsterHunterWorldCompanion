package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton mSearchBtn;
    private SearchView mSearchView;
    private FloatingActionButton mBackBtn;

    //val
    private List<Monster> mMonsterList;

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

        mSearchView = findViewById(R.id.monster_search_view);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mSearchBtn.show();
                mSearchView.setVisibility(View.GONE);
                return true;
            }
        });
        setSearchViewListener();
        mSearchBtn = findViewById(R.id.search_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setVisibility(View.VISIBLE);
                mSearchView.setIconified(false);
                mSearchBtn.hide();
            }
        });
        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /** Initiate and setup view model */
    private void setupViewModel() {
        MonsterListViewModel viewModel = ViewModelProviders
                .of(this)
                .get(MonsterListViewModel.class);
        viewModel.getMonsters().observe(this, new Observer<List<Monster>>() {
            @Override
            public void onChanged(List<Monster> monsterList) {
                mMonsterList = monsterList;
                mAdapter.updateDataSet(monsterList);
            }
        });
    }

    /** Set listener to search view */
    private void setSearchViewListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                List<Monster> searchResult = new ArrayList<>();
                for(Monster monster: mMonsterList) {
                    if(monster.getName().toLowerCase().contains(userInput)) {
                        searchResult.add(monster);
                    }
                }
                mAdapter.updateDataSet(searchResult);
                return true;
            }
        });
    }

}
