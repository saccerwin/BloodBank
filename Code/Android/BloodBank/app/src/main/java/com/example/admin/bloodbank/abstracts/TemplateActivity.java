package com.example.admin.bloodbank.abstracts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.admin.bloodbank.utils.TemplateUtils;


public abstract class TemplateActivity extends AppCompatActivity {
    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initRootView(Bundle savedInstanceState);

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void loadData(Bundle savedInstanceState);

    protected void initActionBar(@NonNull ActionBar actionBar) {

    }

    protected void initHLFragment(Class<? extends TemplateHLFragment> clazz) {
        String tag = clazz.getName();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(getContext(), clazz.getName());
            getSupportFragmentManager().beginTransaction()
                    .add(fragment, tag)
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onCreate(savedInstanceState);
        initRootView(savedInstanceState);
        initUI(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            initActionBar(actionBar);
        }
        loadData(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getSupportActionBar() != null) {
                if ((getSupportActionBar().getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) > 0) {
                    TemplateUtils.navigateUp(this);
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public TemplateActivity getContext() {
        return this;
    }

    public static void startActivity(@NonNull Context context, @NonNull Class<? extends android.app.Activity> cls, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivityForResult(@NonNull android.app.Activity activity, @NonNull Class<? extends android.app.Activity> cls, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends android.app.Activity> cls, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent(fragment.getContext(), cls);
        if (bundle != null) intent.putExtras(bundle);
        fragment.startActivityForResult(intent, requestCode);
    }
}
