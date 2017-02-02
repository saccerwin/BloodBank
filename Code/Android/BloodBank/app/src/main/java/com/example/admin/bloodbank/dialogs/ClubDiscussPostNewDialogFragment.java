package com.example.admin.bloodbank.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateCustomDialogFragment;

/**
 * Created by Admin on 18/01/2017.
 */

public class ClubDiscussPostNewDialogFragment extends TemplateCustomDialogFragment {

    ImageButton imgBtnDismiss;
    Button btnDone;
    EditText edtPostNew;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_post_new_discuss,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        imgBtnDismiss = (ImageButton) view.findViewById(R.id.img_btn_dismiss);
        edtPostNew = (EditText)view.findViewById(R.id.edt_post_new_discuss);
        btnDone = (Button)view.findViewById(R.id.btn_done);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setSizeDialog();
        imgBtnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dissMissDialog();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),edtPostNew.getText().toString(),Toast.LENGTH_SHORT).show();
                dissMissDialog();
            }
        });
    }

    private void dissMissDialog() {
        getDialog().dismiss();

    }
    private void setSizeDialog() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight()/2;
        getDialog().getWindow().setLayout(width,height);

    }
}
