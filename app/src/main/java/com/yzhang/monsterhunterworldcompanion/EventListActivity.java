package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private TextView mIndicatorTv;
    private ProgressBar mProgressBar;
    private RecyclerView mEventListRv;
    private EventListAdapter mAdapter;
    private FloatingActionButton mBackBtn;

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
            mProgressBar.setVisibility(View.VISIBLE);
            mIndicatorTv.setText(getString(R.string.event_loading_indicator_text));
            if(isNetworkAvailable()) {
                MainActivity.getEvents(mAdapter, mLoadingIndicator);
            } else {
                mProgressBar.setVisibility(View.GONE);
                mIndicatorTv.setText(getString(R.string.network_connectivity_indicator_text));
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mAdapter.getEventList() != null) {
            outState.putParcelableArrayList(EVENT_LIST_KEY, (ArrayList)mAdapter.getEventList());
        }
    }
    /** Life cycle end */

    /** Check network connectivity */
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    /** Initiate views */
    private void initViews() {
        mLoadingIndicator = findViewById(R.id.loading_indicator);
        mIndicatorTv = findViewById(R.id.tv_indicator_text);
        mProgressBar = findViewById(R.id.progress_bar);
        mEventListRv = findViewById(R.id.rv_event_list);
        mEventListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventListAdapter(this, null);
        mEventListRv.setAdapter(mAdapter);
        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
