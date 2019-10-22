package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.yzhang.monsterhunterworldcompanion.R;

import java.util.List;

public class ArmorSetSkillListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<String> mSkillNameList;
    private List<Integer> mSkillLevelList;

    public ArmorSetSkillListAdapter(
            Context context,
            List<String> skillNameList,
            List<Integer> skillLevelList) {
        this.mContext = context;
        this.mSkillNameList = skillNameList;
        this.mSkillLevelList = skillLevelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_skill, parent, false);
        SkillViewHolder viewHolder = new SkillViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO: set skill icon
        ((SkillViewHolder) holder).skillName.setText(mSkillNameList.get(position));
        ((SkillViewHolder) holder).skillLevel.setText("Lv." + mSkillLevelList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mSkillNameList == null || mSkillNameList.isEmpty()) {
            return 0;
        } else {
            return mSkillNameList.size();
        }
    }

    public void updateDataSet(List<String> newSkillNameList, List<Integer> newSkillLevelList) {
        mSkillNameList = newSkillNameList;
        mSkillLevelList = newSkillLevelList;
    }

    class SkillViewHolder extends ViewHolder {

        private ImageView skillIcon;
        private TextView skillName;
        private TextView skillLevel;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillIcon = itemView.findViewById(R.id.iv_skill_icon);
            skillName = itemView.findViewById(R.id.tv_skill_name);
            skillLevel = itemView.findViewById(R.id.tv_skill_level);
        }
    }

}
