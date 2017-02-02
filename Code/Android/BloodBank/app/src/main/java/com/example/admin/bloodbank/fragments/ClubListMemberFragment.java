package com.example.admin.bloodbank.fragments;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.activities.ProfileSearchActivity;
import com.example.admin.bloodbank.adapters.ListMemberGroupAdapter;
import com.example.admin.bloodbank.interfaces.OnClickItemListenerInterface;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;
import com.example.admin.bloodbank.objects.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17/01/2017.
 */

public class ClubListMemberFragment extends TemplateFragment {
    private List<Member> list = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_club_list_member,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
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
        return list;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        final ListMemberGroupAdapter adapter = new ListMemberGroupAdapter(getData());
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
                TemplateActivity.startActivity(getActivity(), ProfileSearchActivity.class,null);
            }

            @Override
            public void onLongClickItemListenner(View view, final int position) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Xóa thành viên")
                        .setMessage("Bạn muốn xóa thành viên " + list.get(position).getName() + " ra khỏi clb?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(position);
                                adapter.notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        adapter.setProfileListenerInterface(new ProfileListenerInterface() {
            @Override
            public void onItemAvatarClick(View view, int position) {
            }

            @Override
            public void onItemCallClick(View view, int position) {
                Toast.makeText(getActivity().getBaseContext(),"goi cho " + position,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemMessageClick(View view, int position) {
                Toast.makeText(getActivity().getBaseContext(),"nhan tin cho " + position,Toast.LENGTH_SHORT).show();

            }
        });

    }
}
