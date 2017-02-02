package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.interfaces.PostListenerInterface;
import com.example.admin.bloodbank.objects.PostNews;

import java.util.List;

/**
 * Created by Admin on 10/01/2017.
 */

public class PostNewsAdapter extends TemplateAdapter<PostNewsAdapter.ViewHolderItem> {

    private List<PostNews> listItems;

    private PostListenerInterface mPostListenerInterface;


    public void setOnClickItemListenner(final PostListenerInterface mPostListenerInterface) {
        this.mPostListenerInterface = mPostListenerInterface;
    }
    public PostNewsAdapter(List<PostNews> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(this,parent);
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, int position) {
        holder.setData(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public class ViewHolderItem extends TemplateViewHolder {
        TextView tvNameClub, tvTimePost, tvDesscription,tvComment,tvShare;
        Button btnCountMemberRegisterDonation,btnViewDetailClub;


        public ViewHolderItem(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_items_news);
        }

        protected void initUI() {
            tvNameClub = (TextView) itemView.findViewById(R.id.tv_name_club);
            tvTimePost = (TextView) itemView.findViewById(R.id.tv_time_post);
            tvDesscription = (TextView) itemView.findViewById(R.id.tv_description);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            tvShare = (TextView) itemView.findViewById(R.id.tv_share);
            btnCountMemberRegisterDonation = (Button) itemView.findViewById(R.id.btn_count_register);
            btnViewDetailClub = (Button) itemView.findViewById(R.id.btnViewDetailClub);
            btnCountMemberRegisterDonation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mPostListenerInterface != null) {
                        mPostListenerInterface.onClickMemberRegisterListenner(view,getAdapterPosition());
                    }
                }
            });
            tvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mPostListenerInterface != null) {
                        mPostListenerInterface.onClickCommentListenner(view,getAdapterPosition());
                    }
                }
            });
            btnViewDetailClub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mPostListenerInterface != null) {
                        mPostListenerInterface.onClickBtnViewDetailClubListenner(view,getAdapterPosition());
                    }
                }
            });
        }

        public void setData(PostNews postNews) {
            tvNameClub.setText(postNews.getNameClub());
            tvTimePost.setText(postNews.getTimePost());
            tvDesscription.setText(postNews.getDescription());
        }
    }


}
