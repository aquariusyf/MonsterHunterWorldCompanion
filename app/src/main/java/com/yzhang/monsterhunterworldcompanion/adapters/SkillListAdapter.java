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
import com.yzhang.monsterhunterworldcompanion.appdatabase.skill.Skill;

import java.util.List;

public class SkillListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Skill> mSkillList;
    private MonsterListAdapter.OnListItemClickListener mListener;

    public SkillListAdapter(
            Context context,
            List<Skill> skillList,
            MonsterListAdapter.OnListItemClickListener listener) {
        this.mContext = context;
        this.mSkillList = skillList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_skill, parent, false);
        SingleSkillViewHolder viewHolder = new SingleSkillViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(getSkillIcon(mSkillList.get(position).getId()))
                .placeholder(R.drawable.skill_place_holder)
                .into(((SingleSkillViewHolder) holder).skillIcon);
        ((SingleSkillViewHolder) holder).skillName.setText(mSkillList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(mSkillList == null || mSkillList.isEmpty()) {
            return 0;
        } else {
            return mSkillList.size();
        }
    }

    public void updateDataSet(List<Skill> newSkillList) {
        this.mSkillList = newSkillList;
        notifyDataSetChanged();
    }

    public Skill getCurrentSkill(int position) {
        return mSkillList.get(position);
    }

    private int getSkillIcon(int id) {
        int resourceId = mContext.getResources()
                .getIdentifier("s" + id, "drawable", mContext.getPackageName());
        return resourceId;
    }

    class SingleSkillViewHolder extends ViewHolder implements View.OnClickListener {

        private ImageView skillIcon;
        private TextView skillName;

        public SingleSkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillIcon = itemView.findViewById(R.id.iv_single_skill_icon);
            skillName = itemView.findViewById(R.id.tv_single_skill_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

}
