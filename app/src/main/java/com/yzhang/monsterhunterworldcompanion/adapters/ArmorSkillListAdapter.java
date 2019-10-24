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

import com.bumptech.glide.Glide;
import com.yzhang.monsterhunterworldcompanion.R;

import java.util.HashMap;
import java.util.List;

public class ArmorSkillListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static Context mContext;
    private List<String> mSkillNameList;
    private List<Integer> mSkillLevelList;
    private HashMap<String, Integer> mSKillNameIdMap;

    public ArmorSkillListAdapter(
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
        if(mSKillNameIdMap != null && !mSKillNameIdMap.isEmpty()) {
            Glide.with(mContext)
                    .load(getSkillIcon(mSKillNameIdMap.get(mSkillNameList.get(position))))
                    .placeholder(R.drawable.skill_place_holder)
                    .into(((SkillViewHolder) holder).skillIcon);
        }
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

    public void updateDataSet(
            List<String> newSkillNameList,
            List<Integer> newSkillLevelList,
            HashMap<String, Integer> map) {
        mSkillNameList = newSkillNameList;
        mSkillLevelList = newSkillLevelList;
        mSKillNameIdMap = map;
        notifyDataSetChanged();
    }

    private static int getSkillIcon(int id) {
        int resourceId = mContext.getResources()
                .getIdentifier("s" + id, "drawable", mContext.getPackageName());
        return resourceId;
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
