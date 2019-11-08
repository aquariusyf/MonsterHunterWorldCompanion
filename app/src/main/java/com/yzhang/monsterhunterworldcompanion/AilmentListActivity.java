package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yzhang.monsterhunterworldcompanion.adapters.AilmentListAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.ailment.Ailment;
import com.yzhang.monsterhunterworldcompanion.viewmodels.AilmentListViewModel;

import java.util.List;

public class AilmentListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = AilmentListActivity.class.getSimpleName();

    //UI
    private RecyclerView mAilmentListRv;
    private AilmentListAdapter mAdapter;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailment_list);

        initViews();
        setupViewModel();
    }
    /** Life cycle end */

    /** Initiate views */
    private void initViews() {
        mAilmentListRv = findViewById(R.id.rv_ailment_list);
        mAilmentListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AilmentListAdapter(this, null);
        mAilmentListRv.setAdapter(mAdapter);
    }

    /** Setup view model */
    private void setupViewModel() {
        AilmentListViewModel viewModel = ViewModelProviders
                .of(this)
                .get(AilmentListViewModel.class);
        viewModel.getAilments().observe(this, new Observer<List<Ailment>>() {
            @Override
            public void onChanged(List<Ailment> ailments) {
                mAdapter.updateDataSet(ailments);
            }
        });
    }

}
