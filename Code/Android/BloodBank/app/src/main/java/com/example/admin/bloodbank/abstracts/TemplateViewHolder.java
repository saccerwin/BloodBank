package com.example.admin.bloodbank.abstracts;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.utils.TemplateUtils;


public abstract class TemplateViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerView.Adapter<? extends TemplateViewHolder> adapter;
    private final Context context;

    public TemplateViewHolder(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, View itemView) {
        super(itemView);
        this.adapter = adapter;
        this.context = itemView.getContext();
        initUI();
    }

    public TemplateViewHolder(RecyclerView.Adapter<? extends TemplateViewHolder> adapter, ViewGroup parent, @LayoutRes int layout) {
        this(adapter, LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    public RecyclerView.Adapter<? extends TemplateViewHolder> getAdapter() {
        return adapter;
    }

    public final Context getContext() {
        return context;
    }

    public final Activity getActivity() {
        return TemplateUtils.getActivityFromContext(context);
    }

    protected abstract void initUI();
}
