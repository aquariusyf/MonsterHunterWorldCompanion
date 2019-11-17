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
import com.yzhang.monsterhunterworldcompanion.appdatabase.events.EventQuest;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<EventQuest> mEventList;

    public EventListAdapter(Context context, List<EventQuest> eventList) {
        this.mContext = context;
        this.mEventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_item_event, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventQuest event = mEventList.get(position);
        ((EventViewHolder) holder).name.setText(event.getName());
        ((EventViewHolder) holder).rank.setText(event.getQuestRank() + "");
        ((EventViewHolder) holder).description.setText(event.getDescription());
        ((EventViewHolder) holder).location.setText(event.getLocation().getName());
        ((EventViewHolder) holder).requirement.setText(event.getRequirements());
        ((EventViewHolder) holder).condition.setText(event.getSuccessConditions());

        String start = event.getStartTimestamp().toString();
        String end = event.getEndTimestamp().toString();

        ((EventViewHolder) holder).available.setText(start + "\n                         ~" + "\n" + end);
    }

    @Override
    public int getItemCount() {
        if(mEventList == null || mEventList.isEmpty()) {
            return 0;
        } else {
            return mEventList.size();
        }
    }

    public void updateDataSet(List<EventQuest> newEventList) {
        mEventList = newEventList;
        notifyDataSetChanged();
    }

    public List<EventQuest> getEventList() {
        return mEventList;
    }

    class EventViewHolder extends ViewHolder {

        private TextView name;
        private TextView rank;
        private TextView description;
        private TextView location;
        private TextView requirement;
        private TextView condition;
        private TextView available;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_event_name);
            rank = itemView.findViewById(R.id.tv_event_rank);
            description = itemView.findViewById(R.id.tv_event_description);
            location = itemView.findViewById(R.id.tv_location);
            requirement = itemView.findViewById(R.id.tv_requirements);
            condition = itemView.findViewById(R.id.tv_conditions);
            available = itemView.findViewById(R.id.tv_available);
        }

    }

}
