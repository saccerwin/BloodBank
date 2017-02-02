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

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.activities.CommentActivity;
import com.example.admin.bloodbank.adapters.PostNewDiscussGroupAdapter;
import com.example.admin.bloodbank.dialogs.ClubDiscussPostNewDialogFragment;
import com.example.admin.bloodbank.dialogs.MemberRegisterDonationDialogFragment;
import com.example.admin.bloodbank.interfaces.PostListenerInterface;
import com.example.admin.bloodbank.objects.PostNews;

import java.util.ArrayList;
import java.util.List;


public class ClubDiscussFragment extends TemplateFragment {
    private List<PostNews> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fabPostNews;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_club_discuss,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
         recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
         fabPostNews = (FloatingActionButton)view.findViewById(R.id.fab_postNews);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupRecyclerView();
    }

    private List<PostNews> getData() {
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        list.add(new PostNews("CLB Ban Mai Xanh", "15h00 - 15/01/2017", "Sáng nay một bệnh nhi đang điều trị tai hồi sức cấp cứu bv 600 giường . Cần hỗ trợ 1 đơn vị tiểu cầu máy nhóm O . Vậy bạn Nam nào trên 55kg . Có thể giúp được . Vui lòng liên lạc mình 0905119135 ( Bình ) \n" +
                "P/S Đồng cảm đơn giản là sẻ chia ..."));
        return list;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PostNewDiscussGroupAdapter adapter = new PostNewDiscussGroupAdapter(getData());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                outRect.set(20, position == 0 ? 10 : 0, 20, position == list.size() ? 0 : 30);

            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnClickItemListenner(new PostListenerInterface() {
            @Override
            public void onClickMemberRegisterListenner(View view, int position) {
                FragmentManager fragmentManager = getFragmentManager();
                MemberRegisterDonationDialogFragment dialogFragment = new MemberRegisterDonationDialogFragment();
                dialogFragment.show(fragmentManager,"fm");
            }

            @Override
            public void onClickCommentListenner(View view, int position) {
                TemplateActivity.startActivity(getActivity(), CommentActivity.class,null);
            }

            @Override
            public void onClickShareListenner(View view, int position) {
            }

            @Override
            public void onClickBtnViewDetailClubListenner(View view, int position) {

            }
        });


        isScrollHideFab();
        fabPostNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void isScrollHideFab() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {  // dy > 0 touch down, dy < 0 touch up
                    fabPostNews.hide();
                }
                if(dy < 0) {
                    fabPostNews.show();
                }
            }
        });
    }

    private void showDialog() {
        FragmentManager fm = getFragmentManager();
        ClubDiscussPostNewDialogFragment dialog = new ClubDiscussPostNewDialogFragment();
        dialog.show(fm,"showDialogPostNew");
    }

}
