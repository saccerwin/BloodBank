package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;

/**
 * Created by Admin on 10/02/2017.
 */

public class InstructionFeatureActivity extends TemplateActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private WebView webView;
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_instuction);

    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        webView = (WebView)findViewById(R.id.web_view_about);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.instruction_feature));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }





}
