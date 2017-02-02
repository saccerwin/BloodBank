package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.interfaces.OnClickItemListenerInterface;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;
import com.example.admin.bloodbank.objects.Member;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 11/01/2017.
 */

public class ListResultSearchBloodGroupAdapter extends TemplateAdapter<ListResultSearchBloodGroupAdapter.ItemVH> {
    private List<Member> listItems;
    private OnClickItemListenerInterface onClickItemListenerInterface;
    private ProfileListenerInterface profileListenerInterface;
    public ListResultSearchBloodGroupAdapter(List<Member> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(this, parent);
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        holder.setData(listItems.get(position));
    }
    public void setProfileListenerInterface(final ProfileListenerInterface profileListenerInterface) {
        this.profileListenerInterface = profileListenerInterface;
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }


    public void setItemOnClick(final OnClickItemListenerInterface onClickItemListenerInterface) {
        this.onClickItemListenerInterface = onClickItemListenerInterface;
    }

    public class ItemVH extends TemplateViewHolder {
        public TextView tvName, tvPosition, tvNameClub;
        public CircleImageView imgAvatar;
        public ImageView imgBlood, imgSms, imgPhone;
        public LinearLayout llLayoutProfileSearch;

        public ItemVH(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_result_search_blood_group);
        }

        protected void initUI() {
            tvName = (TextView) itemView.findViewById(R.id.tv_name_user);
            tvPosition = (TextView) itemView.findViewById(R.id.tv_position);
            tvNameClub = (TextView) itemView.findViewById(R.id.tv_name_club);
            imgAvatar = (CircleImageView) itemView.findViewById(R.id.image_circle_avatar);
            imgBlood = (ImageView) itemView.findViewById(R.id.imgView_icon_blood);
            imgSms = (ImageView) itemView.findViewById(R.id.imgView_icon_sms);
            imgPhone = (ImageView) itemView.findViewById(R.id.imgView_icon_phone);
            llLayoutProfileSearch = (LinearLayout)itemView.findViewById(R.id.llLayoutProfileSearch);

            llLayoutProfileSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickItemListenerInterface != null) {
                        onClickItemListenerInterface.onClickItemListenner(view,getAdapterPosition());
                    }
                }
            });
//            tvName.setOnClickListener(this);
//            imgAvatar.setOnClickListener(this);
//            tvNameClub.setOnClickListener(this);
//            tvNameClub.setOnClickListener(this);
            imgPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(profileListenerInterface != null) {
                        profileListenerInterface.onItemCallClick(view,getAdapterPosition());
                    }
                }
            });
            imgSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(profileListenerInterface != null) {
                        profileListenerInterface.onItemMessageClick(view,getAdapterPosition());
                    }
                }
            });
        }

        public void setData(Member member) {
            tvName.setText(member.getName());
            tvPosition.setText(member.getPosition());
            tvNameClub.setText(member.getNameClub());
        }


    }
}
