package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.MemberRegisterDialogAdapter;
import com.example.admin.bloodbank.objects.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 13/02/2017.
 */

public class MemberRegisterDonationActivity extends TemplateActivity {
    private List<Member> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_member_register_donation);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
        setupRecyclerView();
    }

    private List<Member> getData() {
        list.add(new Member("Đặng Duy Hậu","","Admin","CLB Máu Nóng Yêu Thương Đà Nẵng","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        return list;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MemberRegisterDialogAdapter adapter = new MemberRegisterDialogAdapter(getData());
        recyclerView.setAdapter(adapter);
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.text_list_member_register));
            toolbarTitle.setTextSize(14);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_member_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return false;
    }


}
