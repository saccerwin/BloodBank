package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.interfaces.OnClickItemListenerInterface;
import com.example.admin.bloodbank.objects.Club;

import java.util.List;

/**
 * Created by Admin on 11/01/2017.
 */

public class ListClubAdapter extends TemplateAdapter<ListClubAdapter.ItemVH> {
    private List<Club> listItems;
    private OnClickItemListenerInterface onClickItemListenerInterface;
    public ListClubAdapter(List<Club> listItems) {
        this.listItems = listItems;
    }
    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(this,parent);
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {

    }
    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }


    public void  setItemOnClick(final OnClickItemListenerInterface onClickItemListenerInterface) {
        this.onClickItemListenerInterface = onClickItemListenerInterface;
    }

     public class ItemVH extends TemplateViewHolder implements View.OnClickListener {
        private TextView tvNameClub,tvCountMember,tvAddress,tvAddgressFacebook;
        private ImageView imgViewLogoClub;

         public ItemVH(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
             super(adapter, parent, R.layout.list_item_club);
         }

         @Override
         protected void initUI() {
            tvNameClub = (TextView)itemView.findViewById(R.id.tv_name_club);
            tvCountMember = (TextView)itemView.findViewById(R.id.tv_count_member);
            tvAddress = (TextView)itemView.findViewById(R.id.tv_address);
            tvAddgressFacebook = (TextView)itemView.findViewById(R.id.tv_facebook);
            imgViewLogoClub = (ImageView) itemView.findViewById(R.id.imgView_logo_club);
             itemView.setOnClickListener(this);
         }

         public void setData(Club club) {
             tvNameClub.setText(club.getNameClub());
             tvCountMember.setText(club.getCountMember());
             tvAddress.setText("Địa chỉ: " +club.getAddress());
             tvAddgressFacebook.setText("Facebook: " +club.getAddressFacebook());
         }

         @Override
         public void onClick(View view) {
             if(onClickItemListenerInterface != null) {
                 onClickItemListenerInterface.onClickItemListenner(view,getAdapterPosition());
             }
         }
     }
}
