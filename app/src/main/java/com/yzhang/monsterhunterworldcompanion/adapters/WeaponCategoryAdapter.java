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

import java.util.ArrayList;
import java.util.List;

public class WeaponCategoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Pair<String, String>> mCategoryList;
    private MonsterListAdapter.OnListItemClickListener mListener;

    public WeaponCategoryAdapter(Context context, MonsterListAdapter.OnListItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
        createCategoryList();
    }

    private void createCategoryList() {
        mCategoryList = new ArrayList<>();
        Pair<String, String> greatSword = new Pair<>("Great Sword", "great-sword");
        mCategoryList.add(greatSword);
        Pair<String, String> longSword = new Pair<>("Long Sword", "long-sword");
        mCategoryList.add(longSword);
        Pair<String, String> swordAndShield = new Pair<>("Sword&Shield", "sword-and-shield");
        mCategoryList.add(swordAndShield);
        Pair<String, String> dualBlades = new Pair<>("Dual Blades", "dual-blades");
        mCategoryList.add(dualBlades);
        Pair<String, String> hammer = new Pair<>("Hammer", "hammer");
        mCategoryList.add(hammer);
        Pair<String, String> huntingHorn = new Pair<>("Hunting Horn", "hunting-horn");
        mCategoryList.add(huntingHorn);
        Pair<String, String> lance = new Pair<>("Lance", "lance");
        mCategoryList.add(lance);
        Pair<String, String> gunLance = new Pair<>("Gun Lance", "gunlance");
        mCategoryList.add(gunLance);
        Pair<String, String> switchAxe = new Pair<>("Switch Axe", "switch-axe");
        mCategoryList.add(switchAxe);
        Pair<String, String> chargeBlade = new Pair<>("Charge Blade", "charge-blade");
        mCategoryList.add(chargeBlade);
        Pair<String, String> insectGlaive = new Pair<>("Insect Glaive", "insect-glaive");
        mCategoryList.add(insectGlaive);
        Pair<String, String> lightBowGun = new Pair<>("Light Bowgun", "light-bowgun");
        mCategoryList.add(lightBowGun);
        Pair<String, String> heavyBowGun = new Pair<>("Heavy Bowgun", "heavy-bowgun");
        mCategoryList.add(heavyBowGun);
        Pair<String, String> bow = new Pair<>("Bow", "bow");
        mCategoryList.add(bow);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_weapon_category, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((CategoryViewHolder) holder).categoryName.setText(mCategoryList.get(position).first);
        Glide.with(mContext)
                .load(getCategoryIcon(position))
                .placeholder(R.drawable.weapon0)
                .into(((CategoryViewHolder) holder).categoryIcon);
    }

    @Override
    public int getItemCount() {
        if(mCategoryList == null || mCategoryList.isEmpty()) {
            return 0;
        } else {
            return mCategoryList.size();
        }
    }

    public Pair<String, String> getWeaponCategory(int position) {
        return mCategoryList.get(position);
    }

    private int getCategoryIcon(int position) {
        int resourceId = mContext.getResources()
                .getIdentifier("weapon" + position, "drawable", mContext.getPackageName());
        return resourceId;
    }

    class CategoryViewHolder extends ViewHolder implements View.OnClickListener {

        private ImageView categoryIcon;
        private TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.iv_weapon_category_icon);
            categoryName = itemView.findViewById(R.id.tv_weapon_category_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

}
