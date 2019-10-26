package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;

import java.util.List;

public class SkillRankListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Skill.SkillRank> mSkillRankList;

    public SkillRankListAdapter(Context context, List<Skill.SkillRank> skillRankList) {
        this.mContext = context;
        this.mSkillRankList = skillRankList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_skill_rank, parent, false);
        SkillRankViewHolder viewHolder = new SkillRankViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((SkillRankViewHolder) holder).rankIndicator
                .setText("Lv." + mSkillRankList.get(position).getLevel());
        ((SkillRankViewHolder) holder).rankDescription
                .setText(mSkillRankList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if(mSkillRankList == null || mSkillRankList.isEmpty()) {
            return 0;
        } else {
            return mSkillRankList.size();
        }
    }

    public void updateDataSet(List<Skill.SkillRank> newSkillRankList) {
        this.mSkillRankList = newSkillRankList;
        notifyDataSetChanged();
    }

    class SkillRankViewHolder extends ViewHolder {

        private TextView rankIndicator;
        private TextView rankDescription;

        public SkillRankViewHolder(@NonNull View itemView) {
            super(itemView);
            rankIndicator = itemView.findViewById(R.id.tv_skill_rank_indicator);
            rankDescription = itemView.findViewById(R.id.tv_skill_rank_description);
        }
    }

}
