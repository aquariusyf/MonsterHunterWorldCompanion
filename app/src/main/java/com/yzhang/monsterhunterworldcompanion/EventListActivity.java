package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yzhang.monsterhunterworldcompanion.adapters.EventListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.events.EventQuest;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = EventListActivity.class.getSimpleName();
    private static final String EVENT_LIST_KEY = "event-list";

    //ui
    private LinearLayout mLoadingIndicator;
    private RecyclerView mEventListRv;
    private EventListAdapter mAdapter;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        initViews();

        if(savedInstanceState != null && savedInstanceState.containsKey(EVENT_LIST_KEY)) {
            mLoadingIndicator.setVisibility(View.GONE);
            ArrayList<EventQuest> eventQuestList;
            eventQuestList = savedInstanceState.getParcelableArrayList(EVENT_LIST_KEY);
            mAdapter.updateDataSet(eventQuestList);
        } else {
            mLoadingIndicator.setVisibility(View.VISIBLE);
            MainActivity.getEvents(mAdapter, mLoadingIndicator);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EVENT_LIST_KEY, (ArrayList)mAdapter.getEventList());
    }
    /** Life cycle end */

    /** Initiate views */
    private void initViews() {
        mLoadingIndicator = findViewById(R.id.loading_indicator);
        mEventListRv = findViewById(R.id.rv_event_list);
        mEventListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventListAdapter(this, null);
        mEventListRv.setAdapter(mAdapter);
    }

}
