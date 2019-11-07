package com.yzhang.monsterhunterworldcompanion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon.Durability;

public class SharpnessColorAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private Durability mDurability;

    public SharpnessColorAdapter(Context context, Durability durability) {
        this.mContext = context;
        this.mDurability = durability;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.single_item_sharpness_color, parent, false);
        ColorViewHolder viewHolder = new ColorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((ColorViewHolder) holder).colorView
                .setBackgroundColor(mContext.getResources().getColor(getColorByPosition(position)));
    }

    @Override
    public int getItemCount() {
        if(mDurability == null) {
            return 0;
        } else {
            int size = 0;
            size += mDurability.getRed();
            size += mDurability.getOrange();
            size += mDurability.getYellow();
            size += mDurability.getGreen();
            size += mDurability.getBlue();
            size += mDurability.getWhite();
            return size;
        }
    }

    public void updateDataSet(Durability newDurability) {
        mDurability = newDurability;
        notifyDataSetChanged();
    }

    private int getColorByPosition(int position) {
        if(position + 1 <= mDurability.getRed()) {
            return R.color.colorSharpnessRed;
        } else if(position + 1 <= mDurability.getRed() + mDurability.getOrange()) {
            return R.color.colorSharpnessOrange;
        } else if(position + 1 <= mDurability.getRed() + mDurability.getOrange()
                + mDurability.getYellow()) {
            return R.color.colorSharpnessYellow;
        } else if(position + 1 <= mDurability.getRed() + mDurability.getOrange()
                + mDurability.getYellow() + mDurability.getGreen()) {
            return R.color.colorSharpnessGreen;
        } else if(position + 1 <= mDurability.getRed() + mDurability.getOrange()
                + mDurability.getYellow() + mDurability.getGreen() + mDurability.getBlue()) {
            return R.color.colorSharpnessBlue;
        } else if(position + 1 <= mDurability.getRed() + mDurability.getOrange()
                + mDurability.getYellow() + mDurability.getGreen()
                + mDurability.getBlue() + mDurability.getWhite()) {
            return R.color.colorSharpnessWhite;
        } else {
            return R.color.colorSharpnessPurple;
        }
    }

    class ColorViewHolder extends ViewHolder {

        private View colorView;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            colorView = itemView.findViewById(R.id.sharpness_color_view);
        }
    }

}
