package com.example.admin.bloodbank.abstracts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class TemplateFragment extends Fragment {
    protected abstract void initData(Bundle savedInstanceState);

    protected abstract View initRootView(LayoutInflater inflater,
                                         ViewGroup container, Bundle savedInstanceState);

    protected abstract void initUI(View view, Bundle savedInstanceState);

    protected abstract void loadData(Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initRootView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        if (intent != null && intent.resolveActivity(getContext().getPackageManager()) != null) {
            super.startActivity(intent);
        }
    }
}
