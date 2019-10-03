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
import com.yzhang.monsterhunterworldcompanion.appdatabase.Monster;

import java.util.List;

public class MonsterListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<Monster> mMonsterList;
    private OnListItemClickListener mListener;

    public MonsterListAdapter(
            Context context,
            List<Monster> monsterList,
            OnListItemClickListener listener) {
        mContext = context;
        mMonsterList = monsterList;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_monster, parent, false);
        MonsterViewHolder viewHolder = new MonsterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((MonsterViewHolder) holder).name.setText(mMonsterList.get(position).getName());
        ((MonsterViewHolder) holder).species.setText(mMonsterList.get(position).getSpecies());
    }

    @Override
    public int getItemCount() {
        if(mMonsterList != null && !mMonsterList.isEmpty()) {
            return mMonsterList.size();
        } else {
            return 0;
        }
    }

    public void updateDataSet(List<Monster> monsterList) {
        mMonsterList = monsterList;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {
        void onItemClick(int position);
    }

    class MonsterViewHolder extends ViewHolder implements View.OnClickListener {

        private TextView name;
        private TextView species;

        public MonsterViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_monster_name);
            species = itemView.findViewById(R.id.tv_monster_species);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

}
