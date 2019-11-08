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

import java.util.List;

public class CoatingListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String CLOSE_RANGE = "close range";
    private static final String POWER = "power";
    private static final String BLAST = "blast";
    private static final String POISON = "poison";
    private static final String PARALYSIS = "paralysis";
    private static final String SLEEP = "sleep";

    private Context mContext;
    private List<String> mCoatingList;

    public CoatingListAdapter(Context context, List<String> coatingList) {
        this.mContext = context;
        this.mCoatingList = coatingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_coating, parent, false);
        CoatingViewHolder viewHolder = new CoatingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(getCoatingIcon(mCoatingList.get(position)))
                .placeholder(R.drawable.close_range_coating_icon)
                .into(((CoatingViewHolder) holder).coatingIcon);
        ((CoatingViewHolder) holder).coatingType.setText(mCoatingList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mCoatingList == null || mCoatingList.isEmpty()) {
            return 0;
        } else {
            return mCoatingList.size();
        }
    }

    public void updateDataSet(List<String> newCoatingList) {
        mCoatingList = newCoatingList;
        notifyDataSetChanged();
    }

    private int getCoatingIcon(String type) {
        switch (type) {
            case CLOSE_RANGE:
                return R.drawable.close_range_coating_icon;
            case POWER:
                return R.drawable.power_coating_icon;
            case BLAST:
                return R.drawable.blast_coating_icon;
            case POISON:
                return R.drawable.poison_coating_icon;
            case PARALYSIS:
                return R.drawable.paralysis_coating_icon;
            case SLEEP:
                return R.drawable.sleep_coating_icon;
            default: return 0;
        }
    }

    class CoatingViewHolder extends ViewHolder {

        private ImageView coatingIcon;
        private TextView coatingType;

        public CoatingViewHolder(@NonNull View itemView) {
            super(itemView);
            coatingIcon = itemView.findViewById(R.id.iv_coating_icon);
            coatingType = itemView.findViewById(R.id.tv_coating_type);
        }

    }

}
