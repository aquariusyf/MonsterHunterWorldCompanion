package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.yzhang.monsterhunterworldcompanion.adapters.MonsterListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.SkillRankListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.SkillListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;
import com.yzhang.monsterhunterworldcompanion.viewmodels.SkillListViewModel;

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


    }

    /** Initiate and setup view model */
    private void setupViewModel() {
        SkillListViewModel viewModel =
                ViewModelProviders.of(this).get(SkillListViewModel.class);
        viewModel.getSkills().observe(this, new Observer<List<Skill>>() {
            @Override
            public void onChanged(List<Skill> skills) {
                mSkillListAdapter.updateDataSet(skills);
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
