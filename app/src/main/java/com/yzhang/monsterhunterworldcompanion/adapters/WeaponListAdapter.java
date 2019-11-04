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

import java.util.List;

public class WeaponListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Weapon> mWeaponList;
    private MonsterListAdapter.OnListItemClickListener mListener;

    public WeaponListAdapter(
            Context context,
            List<Weapon> weaponList,
            MonsterListAdapter.OnListItemClickListener listener) {
        this.mContext = context;
        this.mWeaponList = mWeaponList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_weapon, parent, false);
        WeaponViewHolder viewHolder = new WeaponViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weapon weapon = mWeaponList.get(position);
        Glide.with(mContext)
                .load(weapon.getAssets().getIcon())
                .into(((WeaponViewHolder) holder).weaponIcon);
        ((WeaponViewHolder) holder).weaponName.setText(weapon.getName());
        ((WeaponViewHolder) holder).attackDamage.setText(weapon.getAttack().getDisplay() + "");
        ((WeaponViewHolder) holder).affinity.setText(weapon.getAttributes().getAffinity() + "%");
        if(weapon.getElements() != null && !weapon.getElements().isEmpty()) {
            Glide.with(mContext)
                    .load(getElementIcon(weapon.getElements().get(0).getType()))
                    .into(((WeaponViewHolder) holder).elementIcon);
            if(weapon.getElements().get(0).getHidden()) {
                ((WeaponViewHolder) holder).elementDamage
                        .setText("(" + weapon.getElements().get(0).getDamage() + ")");
            } else {
                ((WeaponViewHolder) holder).elementDamage
                        .setText(weapon.getElements().get(0).getDamage() + "");
            }
        } else {
            ((WeaponViewHolder) holder).elementIcon.setVisibility(View.GONE);
            ((WeaponViewHolder) holder).elementDamage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(mWeaponList == null || mWeaponList.isEmpty()) {
            return 0;
        } else {
            return mWeaponList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

        public void updateDataSet(List<Weapon> newWeaponList) {
        mWeaponList = newWeaponList;
        notifyDataSetChanged();
    }

    private int getElementIcon(String elementType) {
        int resourceId = mContext.getResources()
                .getIdentifier(elementType, "drawable", mContext.getPackageName());
        return resourceId;
    }

    class WeaponViewHolder extends ViewHolder implements View.OnClickListener {

        private ImageView weaponIcon;
        private TextView weaponName;
        private TextView attackDamage;
        private TextView affinity;
        private ImageView elementIcon;
        private TextView elementDamage;

        public WeaponViewHolder(@NonNull View itemView) {
            super(itemView);
            weaponIcon = itemView.findViewById(R.id.iv_weapon_icon);
            weaponName = itemView.findViewById(R.id.tv_weapon_name);
            attackDamage = itemView.findViewById(R.id.tv_attack_damage);
            affinity = itemView.findViewById(R.id.tv_affinity);
            elementIcon = itemView.findViewById(R.id.iv_element_damage_icon);
            elementDamage = itemView.findViewById(R.id.tv_element_damage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }

    }

}
