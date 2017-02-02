package com.example.admin.bloodbank.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.ListResultSearchBloodGroupAdapter;
import com.example.admin.bloodbank.dialogs.JoinClubDialogFragment;
import com.example.admin.bloodbank.interfaces.OnClickItemListenerInterface;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;
import com.example.admin.bloodbank.objects.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 19/01/2017.
 */

public class ViewDetailClubActivity extends TemplateActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private List<Member> list = new ArrayList<>();
    private RecyclerView recyclerView;
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_detail_club);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
        setupRecyclerView();
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.toobar_name_clb));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_join_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_join_club: {
                FragmentManager fragmentManager = getSupportFragmentManager();
                JoinClubDialogFragment dialogFragment = new JoinClubDialogFragment();
                dialogFragment.show(fragmentManager,"fm");
            }
            break;
        }

        return super.onOptionsItemSelected(item);

    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ListResultSearchBloodGroupAdapter adapter = new ListResultSearchBloodGroupAdapter(getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                outRect.set(5,position == 0 || position == 1 ? 20 : 0,5,5);
            }
        });

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
                Toast.makeText(view.getContext(),"goi cho ..."+ position,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemMessageClick(View view, int position) {
                Toast.makeText(view.getContext(),"nhan tin cho ..."+ position,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
