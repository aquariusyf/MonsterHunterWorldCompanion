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
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class AmmoListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Weapon.Ammo> mAmmoList;

    public AmmoListAdapter(Context context, List<Weapon.Ammo> ammoList) {
        this.mContext = context;
        this.mAmmoList = ammoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_ammo, parent,false);
        AmmoViewHolder viewHolder = new AmmoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position == 0){
            ((AmmoViewHolder) holder).ammoLv1.setVisibility(View.VISIBLE);
            ((AmmoViewHolder) holder).ammoLv2.setVisibility(View.VISIBLE);
            ((AmmoViewHolder) holder).ammoLv3.setVisibility(View.VISIBLE);

            ((AmmoViewHolder) holder).ammoIcon.setVisibility(View.INVISIBLE);
            ((AmmoViewHolder) holder).ammoType
                    .setTextColor(mContext.getResources().getColor(R.color.colorGolden));
            ((AmmoViewHolder) holder).ammoLv1
                    .setTextColor(mContext.getResources().getColor(R.color.colorGolden));
            ((AmmoViewHolder) holder).ammoLv2
                    .setTextColor(mContext.getResources().getColor(R.color.colorGolden));
            ((AmmoViewHolder) holder).ammoLv3
                    .setTextColor(mContext.getResources().getColor(R.color.colorGolden));

            ((AmmoViewHolder) holder).ammoType
                    .setText(mContext.getString(R.string.weapon_detail_ammo_type_label_text));
            ((AmmoViewHolder) holder).ammoLv1
                    .setText(mContext.getString(R.string.weapon_detail_ammo_Lv1_label_text));
            ((AmmoViewHolder) holder).ammoLv2
                    .setText(mContext.getString(R.string.weapon_detail_ammo_Lv2_label_text));
            ((AmmoViewHolder) holder).ammoLv3
                    .setText(mContext.getString(R.string.weapon_detail_ammo_Lv3_label_text));
            return;
        } else {
            ((AmmoViewHolder) holder).ammoIcon.setVisibility(View.VISIBLE);
            ((AmmoViewHolder) holder).ammoType
                    .setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            ((AmmoViewHolder) holder).ammoLv1
                    .setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            ((AmmoViewHolder) holder).ammoLv2
                    .setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            ((AmmoViewHolder) holder).ammoLv3
                    .setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        }
        Weapon.Ammo ammo = mAmmoList.get(position - 1);
        if(ammo.getCapacity() == null) {
            return;
        }
        ((AmmoViewHolder) holder).ammoType.setText(ammo.getType());
        Glide.with(mContext)
                .load(getAmmoIcon(ammo.getType()))
                .placeholder(R.drawable.ammo_icon_normal)
                .into(((AmmoViewHolder) holder).ammoIcon);
        ((AmmoViewHolder) holder).ammoLv1.setVisibility(View.VISIBLE);
        ((AmmoViewHolder) holder).ammoLv2.setVisibility(View.VISIBLE);
        ((AmmoViewHolder) holder).ammoLv3.setVisibility(View.VISIBLE);
        List<Integer> capacity = ammo.getCapacity();
        int levels = capacity.size();
        List<TextView> ammoLevelViewList = new ArrayList<>();
        ammoLevelViewList.add(((AmmoViewHolder) holder).ammoLv1);
        ammoLevelViewList.add(((AmmoViewHolder) holder).ammoLv2);
        ammoLevelViewList.add(((AmmoViewHolder) holder).ammoLv3);
        for(int i = 0; i < ammoLevelViewList.size(); i++) {
            if(i < levels) {
                if(capacity.get(i) == 0) {
                    ammoLevelViewList.get(i).setText("-");
                } else {
                    ammoLevelViewList.get(i).setText(capacity.get(i) + "");
                }
            } else {
                ammoLevelViewList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mAmmoList == null || mAmmoList.isEmpty()) {
            return 0;
        } else {
            return mAmmoList.size() + 1;
        }
    }

    public void updateDataSet(List<Weapon.Ammo> newAmmoList) {
        mAmmoList = newAmmoList;
        notifyDataSetChanged();
    }

    private int getAmmoIcon(String type) {
        int resourceId = mContext.getResources()
                .getIdentifier("ammo_icon_" + type, "drawable", mContext.getPackageName());
        return resourceId;
    }

    class AmmoViewHolder extends ViewHolder {

        private ImageView ammoIcon;
        private TextView ammoType;
        private TextView ammoLv1;
        private TextView ammoLv2;
        private TextView ammoLv3;

        public AmmoViewHolder(@NonNull View itemView) {
            super(itemView);
            ammoIcon = itemView.findViewById(R.id.iv_ammo_icon);
            ammoType = itemView.findViewById(R.id.tv_ammo_type);
            ammoLv1 = itemView.findViewById(R.id.tv_ammo_lv1);
            ammoLv2 = itemView.findViewById(R.id.tv_ammo_lv2);
            ammoLv3 = itemView.findViewById(R.id.tv_ammo_lv3);
        }
    }

}
