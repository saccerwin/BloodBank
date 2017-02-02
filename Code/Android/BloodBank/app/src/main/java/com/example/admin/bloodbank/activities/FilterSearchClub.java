package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 11/01/2017.
 */

public class FilterSearchClub extends TemplateActivity {
    MaterialBetterSpinner spinnerCity,spinnerDistrict,spinnerCountMember;
    Button btnSearch;
    Toolbar toolbar;
    TextView toobarTitle;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_filter_search_club);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        spinnerCountMember = (MaterialBetterSpinner)findViewById(R.id.spinner_count_member);
        spinnerCity = (MaterialBetterSpinner)findViewById(R.id.spinner_city);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);
        btnSearch = (Button)findViewById(R.id.btn_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toobarTitle = (TextView)findViewById(R.id.toolbar_title);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupSpinner();
        setupToobar();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toobarTitle.setText(getString(R.string.filter));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: finish();
        }
        return false;
    }

    private void setupSpinner() {
        String [] listCity = getResources().getStringArray(R.array.city);
        String [] listDistrict = getResources().getStringArray(R.array.district);
        String [] listCountMember = getResources().getStringArray(R.array.count_member);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        ArrayAdapter<String> adapterCountMember = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listCountMember);
        spinnerCity.setAdapter(adapterCity);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerCountMember.setAdapter(adapterCountMember);
    }
}
