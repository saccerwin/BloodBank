package com.example.admin.bloodbank.adapters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.interfaces.PostListenerInterface;
import com.example.admin.bloodbank.objects.PostNews;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.admin.bloodbank.R.id.btnViewDetailClub;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class PostNewDiscussGroupAdapter extends TemplateAdapter<PostNewDiscussGroupAdapter.ViewHolderItem> {

    private List<PostNews> listItems;

    private PostListenerInterface mPostListenerInterface;

    public void setOnClickItemListenner(final PostListenerInterface mPostListenerInterface) {
        this.mPostListenerInterface = mPostListenerInterface;
    }

    public PostNewDiscussGroupAdapter(List<PostNews> listItems) {
        this.listItems = listItems;
    }

    public PostNewDiscussGroupAdapter.ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostNewDiscussGroupAdapter.ViewHolderItem(this, parent);
    }

    public void onBindViewHolder(final PostNewDiscussGroupAdapter.ViewHolderItem holder, final int position) {
        holder.setData(listItems.get(position));
        holder.setOnBtnRegisterClick(new ItemBtnRegisterClick() {
            @Override
            public void onItemBtnRegisterClick(View view, int position) {
                final Button btnRegister = (Button) holder.itemView.findViewById(btnViewDetailClub);
                if (position == holder.getAdapterPosition()) {
//                    Toast.makeText(holder.getContext(),"vt:" + position,Toast.LENGTH_SHORT).show();
                    if (btnRegister.getText().equals("Hủy đăng ký")) { // action delete register
                        new AlertDialog.Builder(holder.getContext())
                                .setTitle("Hủy đăng ký")
                                .setMessage("Bạn muốn hủy đăng ký hiến máu?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        btnRegister.setText("Đăng ký");
                                        btnRegister.setBackgroundResource(R.drawable.button_radius_blue_app);
                                        Toast.makeText(holder.getContext(), "Hủy đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    } else { //action register
                        btnRegister.setText("Hủy đăng ký");
                        btnRegister.setBackgroundResource(R.drawable.textview_background_radius);
                        Toast.makeText(holder.getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                if (position != holder.getAdapterPosition()) {
                    btnRegister.setVisibility(View.GONE);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }


    public class ViewHolderItem extends TemplateViewHolder {
        public TextView tvNameClub, tvTimePost, tvDesscription, tvComment, tvShare;
        public CircleImageView imgViewCircleAvatarClub;
        public Button btnCountMemberRegisterDonation, btnViewDetailClub;
        public ItemBtnRegisterClick itemBtnRegisterClick;


        public ViewHolderItem(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_items_news);
        }

        protected void initUI() {
            tvNameClub = (TextView) itemView.findViewById(R.id.tv_name_club);
            tvTimePost = (TextView) itemView.findViewById(R.id.tv_time_post);
            tvDesscription = (TextView) itemView.findViewById(R.id.tv_description);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            tvShare = (TextView) itemView.findViewById(R.id.tv_share);
            imgViewCircleAvatarClub = (CircleImageView) itemView.findViewById(R.id.img_avatar_club);
            btnCountMemberRegisterDonation = (Button) itemView.findViewById(R.id.btn_count_register);
            btnViewDetailClub = (Button) itemView.findViewById(R.id.btnViewDetailClub);
            btnCountMemberRegisterDonation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mPostListenerInterface != null) {
                        mPostListenerInterface.onClickMemberRegisterListenner(view, getAdapterPosition());
                    }
                }
            });
            tvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mPostListenerInterface != null) {
                        mPostListenerInterface.onClickCommentListenner(view, getAdapterPosition());
                    }
                }
            });


            btnViewDetailClub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemBtnRegisterClick != null) { // listener btn register
                        itemBtnRegisterClick.onItemBtnRegisterClick(view, getAdapterPosition());
                    }
//                    if (mPostListenerInterface != null) {
//                        mPostListenerInterface.onClickBtnViewDetailClubListenner(view, getAdapterPosition());
//                    }
                }
            });
        }


        public void setData(PostNews postNews) {
            tvNameClub.setText(postNews.getNameClub());
            tvTimePost.setText(postNews.getTimePost());
            tvDesscription.setText(postNews.getDescription());
            btnViewDetailClub.setText("Đăng ký");
        }

        public void setOnBtnRegisterClick(final ItemBtnRegisterClick itemBtnRegisterClick) {
            this.itemBtnRegisterClick = itemBtnRegisterClick;
        }
    }

    public interface ItemBtnRegisterClick {
        void onItemBtnRegisterClick(View view, int position);
    }
}
