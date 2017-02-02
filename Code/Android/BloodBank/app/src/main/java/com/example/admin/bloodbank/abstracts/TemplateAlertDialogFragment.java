package com.example.admin.bloodbank.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class TemplateAlertDialogFragment extends DialogFragment {
    private AlertDialog dialog;
    private View root;

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract View initRootView(LayoutInflater inflater, Bundle savedInstanceState);

    protected abstract void initUI(AlertDialog dialog, View view);

    protected abstract void loadData();

    @NonNull
    protected abstract AlertDialog initDialog(@Nullable View view, Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public final void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initUI(dialog, root);
        loadData();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        root = initRootView(getActivity().getLayoutInflater(), savedInstanceState);
        dialog = initDialog(root, savedInstanceState);
        return dialog;
    }

    public void show(Context context, String tag) {
        if (context instanceof FragmentActivity) {
            super.show(((FragmentActivity) context).getSupportFragmentManager(), tag);
        }
    }
}
