package com.example.admin.bloodbank.abstracts;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class TemplateHLFragment extends Fragment {

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void loadData(Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initData(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData(savedInstanceState);
    }
}
