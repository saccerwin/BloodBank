package com.example.admin.bloodbank.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateCustomDialogFragment;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 24/01/2017.
 */

public class AddNewHistoryDialogFragment extends TemplateCustomDialogFragment {
    Button btnDone,btnCancel;
    EditText edtNameHospital,edtQualityUnitsDonation;
    private MaterialBetterSpinner spinnerUnitsDonationBlood;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_add_new_history,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        btnCancel = (Button)view.findViewById(R.id.btn_cancel);
        btnDone = (Button)view.findViewById(R.id.btn_done);
        edtNameHospital = (EditText)view.findViewById(R.id.edt_name_hospital);
        edtQualityUnitsDonation = (EditText)view.findViewById(R.id.edt_quality);
        spinnerUnitsDonationBlood = (MaterialBetterSpinner)view.findViewById(R.id.spinner_units_donation_blood);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupSpinner();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void setupSpinner() {
        String[] listUnitsDonationBlood = getResources().getStringArray(R.array.units_donation_blood_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listUnitsDonationBlood);
        spinnerUnitsDonationBlood.setAdapter(adapter);

    }
}
