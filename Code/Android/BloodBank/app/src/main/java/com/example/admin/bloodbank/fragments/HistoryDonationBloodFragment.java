package com.example.admin.bloodbank.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.adapters.HistoryBloodDonationAdapter;
import com.example.admin.bloodbank.dialogs.AddNewHistoryDialogFragment;
import com.example.admin.bloodbank.objects.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class HistoryDonationBloodFragment extends TemplateFragment{
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddNewHistory;
    private TextView tvFullName;
    private ImageView imgViewCoverBlood;
    private List<History> list = new ArrayList<>();
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_blood_donation,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        tvFullName = (TextView)view.findViewById(R.id.tv_fullname);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        fabAddNewHistory = (FloatingActionButton) view.findViewById(R.id.fab_add_new_history);
        imgViewCoverBlood = (ImageView) view.findViewById(R.id.img_view_cover_blood);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupRecyclerView();

        fabAddNewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                AddNewHistoryDialogFragment dialogFragment = new AddNewHistoryDialogFragment();
                dialogFragment.show(fragmentManager, "fm");
            }
        });
    }

    private List<History> getData() {
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        list.add(new History("24/01/2017","Tại bệnh viện đa khoa đà nẵng","Bạn đã hiến 1 tiểu cầu máu"));
        return list;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HistoryBloodDonationAdapter adapter = new HistoryBloodDonationAdapter(getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                outRect.set(20,0,10,0);
            }
        });
    }
}
