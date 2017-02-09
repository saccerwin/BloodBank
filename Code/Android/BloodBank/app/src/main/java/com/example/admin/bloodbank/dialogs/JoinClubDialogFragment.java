package com.example.admin.bloodbank.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.abstracts.TemplateCustomDialogFragment;
import com.example.admin.bloodbank.activities.NavigationDrawerMainActivity;
import com.example.admin.bloodbank.contraints.Contraint;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class JoinClubDialogFragment extends TemplateCustomDialogFragment {
    Button btnCancel,btnDone;
    EditText edtCode;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_join_group,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        btnCancel = (Button)view.findViewById(R.id.btn_cancel);
        btnDone = (Button)view.findViewById(R.id.btn_done);
        edtCode = (EditText)view.findViewById(R.id.edt_code);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtCode.getText().toString().equals("123456")) {
                    Toast.makeText(getActivity(),"Bạn đã nhập sai mã code vui lòng nhập lại",Toast.LENGTH_SHORT).show();
                    edtCode.getText().clear();

                }
                else  {
                    Toast.makeText(getActivity(),"Tham gia clb thành công!",Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                    Bundle bundle = new Bundle();
                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_MEMBER);
                    TemplateActivity.startActivity(getActivity(), NavigationDrawerMainActivity.class,bundle);
                }
            }
        });
    }
}
