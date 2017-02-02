package com.example.admin.bloodbank.abstracts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.admin.bloodbank.R;

public abstract class TemplateActivityFragment extends TemplateActivity {
    private Fragment fragment;

    @Override
    protected void initData(Bundle savedInstanceState) {
        fragment = initFragment();
        fragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fragment);

    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @NonNull
    protected abstract Fragment initFragment();

    public Fragment getFragment() {
        return fragment;
    }
}
