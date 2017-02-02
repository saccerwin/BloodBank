package com.example.admin.bloodbank.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateCustomDialogFragment;
import com.example.admin.bloodbank.adapters.MemberRegisterDialogAdapter;
import com.example.admin.bloodbank.objects.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17/01/2017.
 */

public class MemberRegisterDonationDialogFragment extends TemplateCustomDialogFragment {
    private ImageButton btnDismiss;
    private TextView tvTitle;
    private List<Member> list = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_member_register_donation,container,false);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        tvTitle = (TextView)view.findViewById(R.id.tv_title);
        btnDismiss = (ImageButton) view.findViewById(R.id.img_btn_dismiss);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
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

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupRecyclerView();
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MemberRegisterDialogAdapter adapter = new MemberRegisterDialogAdapter(getData());
        recyclerView.setAdapter(adapter);
    }

}
