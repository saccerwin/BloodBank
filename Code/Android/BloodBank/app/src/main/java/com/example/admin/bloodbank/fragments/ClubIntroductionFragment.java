package com.example.admin.bloodbank.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateFragment;

/**
 * Created by Admin on 17/01/2017.
 */

public class ClubIntroductionFragment extends TemplateFragment {
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_club_intro,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

}
