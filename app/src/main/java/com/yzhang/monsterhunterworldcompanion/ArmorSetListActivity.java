package com.yzhang.monsterhunterworldcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.yzhang.monsterhunterworldcompanion.adapters.ArmorSetPagerAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.armorset.ArmorSet;
import com.yzhang.monsterhunterworldcompanion.fragment.ArmorSetListFragment;

import java.util.ArrayList;
import java.util.List;

public class ArmorSetListActivity extends AppCompatActivity {

    //const
    private static final String LOG_TAG = ArmorSetListActivity.class.getSimpleName();

    //UI
    private List<Fragment> mFragmentList;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private FloatingActionButton mSearchBtn;
    private SearchView mSearchView;
    private FloatingActionButton mBackBtn;

    /** Life cycle begin */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armorset_list);

        initViewPager();
    }
    /** Life cycle end */

    /** Initialize view pager */
    private void initViewPager() {
        mViewPager = findViewById(R.id.view_pager);
        mTab = findViewById(R.id.pager_tab);

        mFragmentList = new ArrayList<>();
        mFragmentList.add(ArmorSetListFragment.newInstance(0));
        mFragmentList.add(ArmorSetListFragment.newInstance(1));
        mFragmentList.add(ArmorSetListFragment.newInstance(2));

        mViewPager.setAdapter(
                new ArmorSetPagerAdapter(getSupportFragmentManager(), mFragmentList, this));
        mViewPager.setOffscreenPageLimit(2);
        mTab.setupWithViewPager(mViewPager);

        mSearchView = findViewById(R.id.armor_search_view);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mSearchBtn.show();
                mSearchView.setVisibility(View.GONE);
                return true;
            }
        });
        setSearchViewListener();
        mSearchBtn = findViewById(R.id.search_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setVisibility(View.VISIBLE);
                mSearchView.setIconified(false);
                mSearchBtn.hide();
            }
        });
        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /** Set listener to search view */
    private void setSearchViewListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<ArmorSet> armorSetList =
                        ((ArmorSetListFragment) mFragmentList.get(mViewPager.getCurrentItem())).getArmorSetList();
                String userInput = newText.toLowerCase();
                List<ArmorSet> searchResult = new ArrayList<>();
                if(armorSetList != null) {
                    for(ArmorSet armorSet: armorSetList) {
                        if(armorSet.getName().toLowerCase().contains(userInput)) {
                            searchResult.add(armorSet);
                        }
                    }
                    ((ArmorSetListFragment) mFragmentList.get(mViewPager.getCurrentItem()))
                            .updateSearchResult(searchResult);
                }
                return true;
            }
        });
    }

}
