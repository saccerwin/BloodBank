package com.example.admin.bloodbank.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateAdapter;
import com.example.admin.bloodbank.abstracts.TemplateViewHolder;
import com.example.admin.bloodbank.objects.Comment;

import java.util.List;

/**
 * Created by Admin on 20/01/2017.
 */

public class CommentAdapter extends TemplateAdapter<CommentAdapter.ItemVH> {

    private List<Comment> list;

    public CommentAdapter(List<Comment> list) {
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
        public ImageView imgViewAvatar;
        public TextView tvDescription;
        public ItemVH(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent) {
            super(adapter, parent, R.layout.list_item_comment);

        }

        @Override
        protected void initUI() {
            imgViewAvatar = (ImageView)itemView.findViewById(R.id.avatar_comment);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_description_comment);
        }
        public void setData(Comment comment) {
            String text = comment.getName() + "  " + comment.getDescriptionComment();
            final SpannableStringBuilder str = new SpannableStringBuilder(text);
            str.setSpan(new ForegroundColorSpan(Color.CYAN),0,comment.getName().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvDescription.setText(str);
        }
    }
}
