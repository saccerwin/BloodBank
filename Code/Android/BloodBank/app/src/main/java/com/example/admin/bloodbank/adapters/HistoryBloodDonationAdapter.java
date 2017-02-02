package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.objects.History;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class HistoryBloodDonationAdapter extends RecyclerView.Adapter<HistoryBloodDonationAdapter.ItemVH> {


    private List<History> list;
    public HistoryBloodDonationAdapter(List<History> list) {
        this.list = list;
    }
    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(this,parent);
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ItemVH extends TemplateViewHolder {
        public TextView tvDate, tvAddressHospital, tvContent;
        public CircleImageView img_avatar;
        public ItemVH(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_history_blood);
        }

        @Override
        protected void initUI() {
            tvDate = (TextView)itemView.findViewById(R.id.tv_date);
            tvAddressHospital = (TextView)itemView.findViewById(R.id.tv_address_hospital);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
            img_avatar = (CircleImageView) itemView.findViewById(R.id.img_avatar);
        }
        public void setData(History history) {
            tvDate.setText(history.getDate());
            tvAddressHospital.setText(history.getAddressHospital());
            tvContent.setText(history.getContentHistory());
        }
    }
}
