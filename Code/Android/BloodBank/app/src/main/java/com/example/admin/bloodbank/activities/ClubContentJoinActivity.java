package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.ViewPagerAdapter;
import com.example.admin.bloodbank.fragments.ClubDiscussFragment;
import com.example.admin.bloodbank.fragments.ClubIntroductionFragment;
import com.example.admin.bloodbank.fragments.ClubListMemberFragment;

/**
 * Created by Admin on 13/02/2017.
 */

public class ClubContentJoinActivity extends TemplateActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_club_content_join);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClubDiscussFragment(), "Thảo luận");
        adapter.addFragment(new ClubListMemberFragment(), "Thành viên CLB");
        adapter.addFragment(new ClubIntroductionFragment(), "Giới thiệu CLB");
        viewPager.setAdapter(adapter);
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(("CLB ....."));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }
}
