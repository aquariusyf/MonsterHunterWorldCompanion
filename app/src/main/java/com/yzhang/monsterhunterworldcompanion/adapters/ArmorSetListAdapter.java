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
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;

import java.util.List;

public class ArmorSetListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<ArmorSet> mArmorSetList;

    public ArmorSetListAdapter(Context context, List<ArmorSet> armorSetList) {
        mContext = context;
        mArmorSetList = armorSetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_armorset, parent, false);
        ArmorSetViewHolder viewHolder = new ArmorSetViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((ArmorSetViewHolder) holder).nameTv.setText(mArmorSetList.get(position).getName());
        ((ArmorSetViewHolder) holder).defenceTv.setText(mArmorSetList.get(position).getTotalDefence() + "");
        ((ArmorSetViewHolder) holder).thunderTv.setText(mArmorSetList.get(position).getThunderRes() + "");
        ((ArmorSetViewHolder) holder).fireTv.setText(mArmorSetList.get(position).getFireRes() + "");
        ((ArmorSetViewHolder) holder).iceTv.setText(mArmorSetList.get(position).getIceRes() + "");
        ((ArmorSetViewHolder) holder).waterTv.setText(mArmorSetList.get(position).getWaterRes() + "");
        ((ArmorSetViewHolder) holder).dragonTv.setText(mArmorSetList.get(position).getDragonRes() + "");
    }

    @Override
    public int getItemCount() {
        if(mArmorSetList == null || mArmorSetList.isEmpty()) {
            return 0;
        } else {
            return mArmorSetList.size();
        }
    }

    public void updateDataSet(List<ArmorSet> newArmorSetList) {
        mArmorSetList = newArmorSetList;
        notifyDataSetChanged();
    }

    class ArmorSetViewHolder extends ViewHolder {

        private TextView nameTv;
        private TextView defenceTv;
        private TextView thunderTv;
        private TextView fireTv;
        private TextView iceTv;
        private TextView waterTv;
        private TextView dragonTv;

        public ArmorSetViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_armorset_name);
            defenceTv = itemView.findViewById(R.id.tv_total_defence);
            thunderTv = itemView.findViewById(R.id.tv_elemental_thunder);
            fireTv = itemView.findViewById(R.id.tv_elemental_fire);
            iceTv = itemView.findViewById(R.id.tv_elemental_ice);
            waterTv = itemView.findViewById(R.id.tv_elemental_water);
            dragonTv = itemView.findViewById(R.id.tv_elemental_dragon);
        }
    }

}
