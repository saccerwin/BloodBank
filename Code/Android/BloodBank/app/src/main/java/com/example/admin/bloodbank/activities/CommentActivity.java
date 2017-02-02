package com.example.admin.bloodbank.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.CommentAdapter;
import com.example.admin.bloodbank.objects.Comment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 20/01/2017.
 */

public class CommentActivity extends TemplateActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private List<Comment> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private CardView cardView;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_comment);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        cardView = (CardView)findViewById(R.id.cardViewNews);
    }


    @Override
    protected void loadData(Bundle savedInstanceState) {
//        setupToobar();
        setupRecyclerView();
    }

    private List<Comment> getData() {
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tkfdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        list.add(new Comment("Vo Dang Khoa","Minh co nhom mau A ai can co the lien he minh, tks"));
        return list;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                outRect.set(5,position == 0 ? 10 : 0,5, position == list.size() ? 150 : 0);
            }
        });
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.toobar_title_comment));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

}
