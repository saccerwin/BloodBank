package com.example.admin.bloodbank.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.objects.Member;

import java.util.List;

/**
 * Created by Admin on 18/01/2017.
 */

public class MemberRegisterDialogAdapter extends TemplateAdapter<MemberRegisterDialogAdapter.ItemVH> {

    private List<Member> list;

    public MemberRegisterDialogAdapter(List<Member> list) {
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
        public TextView tvName;
        public ItemVH(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_register_donation_blood);
        }

        @Override
        protected void initUI() {
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
        }
        public void setData(Member member) {
            tvName.setText(member.getName());
        }
    }


}
