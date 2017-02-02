package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 13/01/2017.
 */

public class ProfileAdapter extends TemplateAdapter<TemplateViewHolder> {
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_CONTACT = 2;
    private static final int TYPE_BODY = 3;
    private String[] title;
    private int[] itemIcon;
    private ProfileListenerInterface profileListenerInterface;

    public ProfileAdapter(String[] title, int[] itemIcon) {
        this.title = title;
        this.itemIcon = itemIcon;
    }


    public TemplateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new ViewHolderItemHeader(this, parent);
        } else if (viewType == TYPE_CONTACT) {
            return new ViewHolderItemContact(this, parent);
        }
        return new ViewHolderItems(this, parent);
    }

    @Override
    public void onBindViewHolder(TemplateViewHolder holder, int position) {
        if (holder instanceof ProfileAdapter.ViewHolderItems) {
            ((ProfileAdapter.ViewHolderItems) holder).setData(title[position - 2], itemIcon[position - 2]);
        }
    }

    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        if (position == 1) return TYPE_CONTACT;
        return TYPE_BODY;
    }


    public void setProfileListenner(final ProfileListenerInterface profileListenerInterface) {
        this.profileListenerInterface = profileListenerInterface;
    }

    @Override
    public int getItemCount() {
        return title.length == 0 ? 0 : title.length + 2;
    }

    private class ViewHolderItems extends TemplateViewHolder implements View.OnLongClickListener {
        TextView tvMenuItem;
        ImageView imgIconItem;

        public ViewHolderItems(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_profile_body);
        }

        @Override
        protected void initUI() {
            tvMenuItem = (TextView) itemView.findViewById(R.id.tv_name_menu);
            imgIconItem = (ImageView) itemView.findViewById(R.id.imgView_icon);
            itemView.setOnLongClickListener(this);
        }

        public void setData(String titleMenu, int itemIcon) {
            tvMenuItem.setText(titleMenu);
            imgIconItem.setImageResource(itemIcon);
        }


        @Override
        public boolean onLongClick(View view) {
            if(profileListenerInterface != null) {
                profileListenerInterface.onItemEditProfileLongClick(view,getAdapterPosition());
            }
            return true;
        }
    }

    private class ViewHolderItemHeader extends TemplateViewHolder {
        public TextView tvNameNav;
        public TextView tvNamePosition;
        public CircleImageView imgAvatar;
        public ImageView imgBlood;

        public ViewHolderItemHeader(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_profile_header);
        }

        @Override
        protected void initUI() {
            tvNamePosition = (TextView) itemView.findViewById(R.id.tv_position);
            tvNameNav = (TextView) itemView.findViewById(R.id.tv_name);
            imgAvatar = (CircleImageView) itemView.findViewById(R.id.image_circle_avatar);
            imgBlood = (ImageView) itemView.findViewById(R.id.imgView_blood);
            imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (profileListenerInterface != null) {
                        profileListenerInterface.onItemAvatarClick(view, getAdapterPosition());
                    }
                }
            });
        }

        public void setData(String name, String position, int avatar) {

        }

    }

    private class ViewHolderItemContact extends TemplateViewHolder {
        public TextView tvCall, tvMessage;

        public ViewHolderItemContact(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_profile_contact);
        }

        @Override
        protected void initUI() {
            tvCall = (TextView) itemView.findViewById(R.id.tv_btn_call);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_btn_sms);

            tvMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (profileListenerInterface != null) {
                        profileListenerInterface.onItemMessageClick(view, getAdapterPosition());
                    }
                }
            });
            tvCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (profileListenerInterface != null) {
                        profileListenerInterface.onItemCallClick(view, getAdapterPosition());
                    }
                }
            });
        }

        public void setData() {

        }

    }

}
