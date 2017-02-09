package com.example.admin.bloodbank.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.ListResultSearchBloodGroupAdapter;
import com.example.admin.bloodbank.interfaces.OnClickItemListenerInterface;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;
import com.example.admin.bloodbank.objects.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/01/2017.
 */

public class ResultSearchBloodGroupActivity extends TemplateActivity {
    private List<Member> list = new ArrayList<>();
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_result_search_blood_group);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
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
            toolbarTitle.setText(getString(R.string.list_search));
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

    private List<Member> getData() {
        list.add(new Member("Đặng Duy Hậu","","Admin","CLB Máu Nóng Yêu Thương Đà Nẵng","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        list.add(new Member("Đặng Duy Hậu","","Thành Viên CLB","CLB Ban Mai Xanh","012649800"));
        return list;
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ListResultSearchBloodGroupAdapter adapter = new ListResultSearchBloodGroupAdapter(getData());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                int position = parent.getChildLayoutPosition(view);
                outRect.set(10, 10 , 10, 10);

            }
        });
        recyclerView.setAdapter(adapter);

        adapter.setItemOnClick(new OnClickItemListenerInterface() {
            @Override
            public void onClickItemListenner(View view, int position) {
                TemplateActivity.startActivity(getContext(),ProfileSearchActivity.class, null);
            }
            @Override
            public void onLongClickItemListenner(View view, int position) {

            }
        });
        adapter.setProfileListenerInterface(new ProfileListenerInterface() {
            @Override
            public void onItemAvatarClick(View view, int position) {

            }

            @Override
            public void onItemCallClick(View view, int position) {
                Toast.makeText(view.getContext(),"goi cho ..." + position ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemMessageClick(View view, int position) {
                Toast.makeText(view.getContext(),"nhan tin cho ..."+ position,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemEditProfileClick(View view, int position) {

            }

        });
    }
}
