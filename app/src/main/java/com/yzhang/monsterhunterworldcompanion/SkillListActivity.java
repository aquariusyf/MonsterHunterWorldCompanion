package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.SkillRankListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.SkillListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;
import com.yzhang.monsterhunterworldcompanion.viewmodels.SkillListViewModel;

import java.util.ArrayList;
import java.util.List;

public class SkillListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = SkillListActivity.class.getSimpleName();

    //UI
    private RecyclerView mSKillListRv;
    private SkillListAdapter mSkillListAdapter;
    private RecyclerView mSkillRankListRv;
    private SkillRankListAdapter mSkillRankListAdapter;
    private NestedScrollView mSkillDetailsView;
    private TextView mSkillEffectTv;
    private FloatingActionButton mSearchBtn;
    private SearchView mSearchView;
    private FloatingActionButton mBackBtn;

    //val
    private List<Skill> mSkillList;

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
        mSkillDetailsView = findViewById(R.id.skill_detail_bottom_sheet);
        mSkillEffectTv = findViewById(R.id.tv_single_skill_description);

        mSkillRankListRv = findViewById(R.id.rv_skill_rank_list);
        mSkillRankListRv.setLayoutManager(new LinearLayoutManager(this));
        mSkillRankListAdapter = new SkillRankListAdapter(this, null);
        mSkillRankListRv.setAdapter(mSkillRankListAdapter);

        mSKillListRv = findViewById(R.id.rv_skill_list);
        mSKillListRv.setLayoutManager(new LinearLayoutManager(this));
        mSkillListAdapter = new SkillListAdapter(
                this,
                null,
                new MonsterListAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mSkillEffectTv.setText(mSkillListAdapter.getCurrentSkill(position).getDescription());
                List<Skill.SkillRank> rankList = mSkillListAdapter.getCurrentSkill(position).getRanks();
                mSkillRankListAdapter.updateDataSet(rankList);
                dragOutBottomSheet();
            }
        });
        mSKillListRv.setAdapter(mSkillListAdapter);

        mSearchView = findViewById(R.id.skill_search_view);
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
                List<Skill> searchResult = new ArrayList<>();
                for(Skill skill: mSkillList) {
                    if(skill.getName().toLowerCase().contains(userInput)) {
                        searchResult.add(skill);
                    }
                }
                mSkillListAdapter.updateDataSet(searchResult);
                return true;
            }
        });
    }

    /** Initiate and setup view model */
    private void setupViewModel() {
        SkillListViewModel viewModel =
                ViewModelProviders.of(this).get(SkillListViewModel.class);
        viewModel.getSkills().observe(this, new Observer<List<Skill>>() {
            @Override
            public void onChanged(List<Skill> skills) {
                mSkillListAdapter.updateDataSet(skills);
                mSkillList = skills;
            }
        });
    }

    /** Drag out bottom sheet for skill rank details */
    private void dragOutBottomSheet() {
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(mSkillDetailsView);
        if(behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            mSkillDetailsView.fullScroll(View.FOCUS_UP); // Reset the scroll position
        }
    }

}
