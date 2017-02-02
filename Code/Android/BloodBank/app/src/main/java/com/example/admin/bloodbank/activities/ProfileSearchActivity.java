package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.ProfileAdapter;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class ProfileSearchActivity extends TemplateActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile_search);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupRecyclerView();
        setupToobar();
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.title_profile_search));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    private void setupRecyclerView() {
        String[] navNameMenu = getResources().getStringArray(R.array.array_item_profile);
        int[] itemIcons = {R.drawable.ic_count_donation_blood, R.drawable.ic_phone_profile, R.drawable.ic_mail, R.drawable.ic_address, R.drawable.ic_birthday, R.drawable.ic_gender, R.drawable.ic_time_donation};
        ProfileAdapter adapter = new ProfileAdapter(navNameMenu, itemIcons);
        adapter.setProfileListenner(new ProfileListenerInterface() {
            @Override
            public void onItemAvatarClick(View view, int position) {

            }

            @Override
            public void onItemCallClick(View view, int position) {
                Toast.makeText(getContext(),"call",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemMessageClick(View view, int position) {
                Toast.makeText(getContext(),"message",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
