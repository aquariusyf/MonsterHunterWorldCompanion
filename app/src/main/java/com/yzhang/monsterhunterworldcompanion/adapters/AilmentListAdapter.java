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
import com.yzhang.monsterhunterworldcompanion.appdatabase.ailment.Ailment;

import java.util.List;

public class AilmentListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Ailment> mAilmentList;

    public AilmentListAdapter(Context context, List<Ailment> ailmentList) {
        this.mContext = context;
        this.mAilmentList = ailmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_ailment, parent, false);
        AilmentViewHolder viewHolder = new AilmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ailmentName = mAilmentList.get(position)
                .getName().replaceAll(" ", "").toLowerCase();
        Glide.with(mContext)
                .load(getAilmentIcon(ailmentName))
                .placeholder(R.drawable.icon_effluvialbuildup)
                .into(((AilmentViewHolder) holder).ailmentIcon);
        ((AilmentViewHolder) holder).ailmentName.setText(mAilmentList.get(position).getName());
        ((AilmentViewHolder) holder).ailmentDescription
                .setText(mAilmentList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if(mAilmentList == null || mAilmentList.isEmpty()) {
            return 0;
        } else {
            return mAilmentList.size();
        }
    }

    public void updateDataSet(List<Ailment> newAilmentList) {
        mAilmentList = newAilmentList;
        notifyDataSetChanged();
    }

    private int getAilmentIcon(String name) {
        int resourceId = mContext.getResources()
                .getIdentifier("icon_" + name, "drawable", mContext.getPackageName());
        return resourceId;
    }

    class AilmentViewHolder extends ViewHolder {

        ImageView ailmentIcon;
        TextView ailmentName;
        TextView ailmentDescription;

        public AilmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ailmentIcon = itemView.findViewById(R.id.iv_ailment_icon);
            ailmentName = itemView.findViewById(R.id.tv_ailment_name);
            ailmentDescription = itemView.findViewById(R.id.tv_ailment_description);
        }
    }

}
