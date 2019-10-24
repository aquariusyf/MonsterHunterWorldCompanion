package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;
import android.util.Pair;
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

public class ArmorSetSkillListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static Context mContext;
    private List<Pair<String, String>> mSetSkillList;
    private List<String> mSetSkillDescriptionList;
    private HashMap<String, Integer> mSKillNameIdMap;

    public ArmorSetSkillListAdapter(
            Context context,
            List<Pair<String, String>> setSkillList,
            List<String> setSkillDescriptionList) {
        this.mContext = context;
        this.mSetSkillList = setSkillList;
        this.mSetSkillDescriptionList = setSkillDescriptionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_set_skill, parent, false);
        SetSkillViewHolder viewHolder = new SetSkillViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mSKillNameIdMap != null && !mSKillNameIdMap.isEmpty()) {
            Glide.with(mContext)
                    .load(getSetSkillIcon(mSKillNameIdMap.get(mSetSkillList.get(position).first)))
                    .placeholder(R.drawable.skill_place_holder)
                    .into(((SetSkillViewHolder) holder).singleSetSKillIcon);
        }
        Glide.with(mContext)
                .load(getNumOfPieceImage(mSetSkillList.get(position).second))
                .into(((SetSkillViewHolder) holder).numOfPieceIcon);
        ((SetSkillViewHolder) holder).singleSetSkillName.setText(mSetSkillList.get(position).first);
        ((SetSkillViewHolder) holder).singleSetSkillDescription.setText(mSetSkillDescriptionList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mSetSkillList == null || mSetSkillList.isEmpty()) {
            return 0;
        } else {
            return mSetSkillList.size();
        }
    }

    public void updateDataSet(
            List<Pair<String, String>> newSetSkillList,
            List<String> newSetSkillDescriptionList,
            HashMap<String, Integer> map) {
        mSetSkillList = newSetSkillList;
        mSetSkillDescriptionList = newSetSkillDescriptionList;
        mSKillNameIdMap = map;
        notifyDataSetChanged();
    }

    private static int getNumOfPieceImage(String numOfPiece) {
        int resourceId = mContext.getResources().getIdentifier(
                "setof" + numOfPiece,
                "drawable",
                mContext.getPackageName());
        return resourceId;
    }

    private static int getSetSkillIcon(int id) {
        int resourceId = mContext.getResources()
                .getIdentifier("s" + id, "drawable", mContext.getPackageName());
        return resourceId;
    }

    class SetSkillViewHolder extends ViewHolder {

        private ImageView numOfPieceIcon;
        private ImageView singleSetSKillIcon;
        private TextView singleSetSkillName;
        private TextView singleSetSkillDescription;

        public SetSkillViewHolder(@NonNull View itemView) {
            super(itemView);
            numOfPieceIcon = itemView.findViewById(R.id.iv_num_of_pieces);
            singleSetSKillIcon = itemView.findViewById(R.id.iv_single_set_skill_icon);
            singleSetSkillName = itemView.findViewById(R.id.tv_single_set_skill_name);
            singleSetSkillDescription = itemView.findViewById(R.id.tv_set_skill_description);
        }
    }

}
