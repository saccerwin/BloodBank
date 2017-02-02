package com.example.admin.bloodbank.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.adapters.ViewPagerAdapter;

/**
 * Created by Admin on 16/01/2017.
 */

public class ManagerClubFragment extends TemplateFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager_club,null);
    }

    protected void initUI(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ClubDiscussFragment(), "Thảo luận");
        adapter.addFragment(new ClubListMemberFragment(), "Thành viên CLB");
        adapter.addFragment(new ClubIntroductionFragment(), "Giới thiệu CLB");
        viewPager.setAdapter(adapter);
    }

}
