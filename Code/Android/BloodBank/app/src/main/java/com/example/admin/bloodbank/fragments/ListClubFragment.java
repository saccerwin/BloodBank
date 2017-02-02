package com.example.admin.bloodbank.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.activities.ViewDetailClubActivity;
import com.example.admin.bloodbank.adapters.ListClubAdapter;
import com.example.admin.bloodbank.interfaces.OnClickItemListenerInterface;
import com.example.admin.bloodbank.objects.Club;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/01/2017.
 */

public class ListClubFragment extends TemplateFragment {
    private List<Club> list = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_club, null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupRecyclerView();
    }



    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListClubAdapter adapter = new ListClubAdapter(getData());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                outRect.set(20,position == 0 ? 5 : 0,15,20);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setItemOnClick(new OnClickItemListenerInterface() {
            @Override
            public void onClickItemListenner(View view, int position) {
                TemplateActivity.startActivity(getActivity(),ViewDetailClubActivity.class,null);
            }

            @Override
            public void onLongClickItemListenner(View view, int position) {

            }
        });

    }

    private List<Club> getData() {
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        list.add(new Club("Số lượng thành viên là: 10323","CLB BAN MAI XANH ĐÀ NẴNG","Hòa Khánh, Đà Nẵng", "CLb Ban Mai Xanh"));
        return list;
    }


}
