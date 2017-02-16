package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class NavigationRecyclerViewAdapter extends TemplateAdapter<TemplateViewHolder> {
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_BODY = 2;
    private String[] title;
    private int[] itemIcon;
    private String name;
    private String decentralizationName;
    private String avatar;

    private OnItemMenuNavClickListener mItemMenuNavClickListener;

    public TemplateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) return new ItemVHHeader(this,parent);
        return new ItemVH(this,parent);
    }

    @Override
    public void onBindViewHolder(TemplateViewHolder holder, int position) {
        if (holder instanceof ItemVH) {
            ((ItemVH) holder).setData(title[position - 1], itemIcon[position - 1]);
        }
        if(holder instanceof ItemVHHeader) {
            ((ItemVHHeader)holder).setData(name,decentralizationName,avatar);
        }

    }

    public NavigationRecyclerViewAdapter(String[] title, int[] itemIcon,String name, String decentralizationName, String avatar) {
        this.title = title;
        this.itemIcon = itemIcon;
        this.name = name;
        this.decentralizationName = decentralizationName;
        this.avatar = avatar;
    }

    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        return TYPE_BODY;
    }

    @Override
    public int getItemCount() {
        return title.length == 0 ? 0 : title.length + 1;
    }

    private class ItemVH extends TemplateViewHolder implements View.OnClickListener {
        TextView tvMenuItem;
        ImageView imgIconItem;

        public ItemVH(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.drawer_item_layout);
        }

        @Override
        protected void initUI() {
            tvMenuItem = (TextView) itemView.findViewById(R.id.tv_name_menu_nav);
            imgIconItem = (ImageView) itemView.findViewById(R.id.imgView_icon_menu);
            itemView.setOnClickListener(this);
        }
        public void setData(String titleMenu, int itemIcon) {
            tvMenuItem.setText(titleMenu);
            imgIconItem.setImageResource(itemIcon);
        }

        @Override
        public void onClick(View view) {
            if (mItemMenuNavClickListener != null) {
                mItemMenuNavClickListener.onItemClick(view, getAdapterPosition());
            }
        }


    }

    private static class ItemVHHeader extends TemplateViewHolder {
        TextView tvNameNav;
        TextView tvNamePosition;
        CircleImageView imgAvatar;

        public ItemVHHeader(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.drawer_header_layout);
        }

        protected void initUI() {
            tvNamePosition = (TextView) itemView.findViewById(R.id.tv_position_header_nav);
            tvNameNav = (TextView) itemView.findViewById(R.id.tv_name_header_nav);
            imgAvatar = (CircleImageView) itemView.findViewById(R.id.image_circle_avatar_header_nav);
        }

        public void setData(String name, String decentralizationName, String avatarUrl) {
            tvNameNav.setText(name);
            tvNamePosition.setText(decentralizationName);
            Picasso.with(getContext())
                    .load(avatarUrl)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.error)
                    .resize(200,200)
                    .into(imgAvatar);
        }
    }

    public interface OnItemMenuNavClickListener {
        void onItemClick(View view, int position);
    }

    public void setmItemMenuNavClickListener(final OnItemMenuNavClickListener mItemMenuNavClickListener) {
        this.mItemMenuNavClickListener = mItemMenuNavClickListener;
    }


}
