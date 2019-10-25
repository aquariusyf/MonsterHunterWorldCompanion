package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.SkillListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;
import com.yzhang.monsterhunterworldcompanion.viewmodels.SkillListViewModel;

import java.util.List;

public class SkillListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = SkillListActivity.class.getSimpleName();

    //UI
    private RecyclerView mSKillListRv;
    private SkillListAdapter mAdapter;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_list);

        initViews();
        setupViewModel();
    }
    /** Life cycle end */


    /** Initiate views and set listener */
    private void initViews() {
        mSKillListRv = findViewById(R.id.rv_skill_list);
        mSKillListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SkillListAdapter(
                this,
                null,
                new MonsterListAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //TODO: go to skill details
            }
        });
        mSKillListRv.setAdapter(mAdapter);
    }

    /** Initiate and setup view model */
    private void setupViewModel() {
        SkillListViewModel viewModel =
                ViewModelProviders.of(this).get(SkillListViewModel.class);
        viewModel.getSkills().observe(this, new Observer<List<Skill>>() {
            @Override
            public void onChanged(List<Skill> skills) {
                mAdapter.updateDataSet(skills);
            }
        });
    }

}
