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
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 24/01/2017.
 */

public class AddNewHistoryDialogFragment extends TemplateCustomDialogFragment {
    Button btnDone,btnCancel;
    EditText edtQualityUnitsDonation;
    private MaterialBetterSpinner spinnerUnitsDonationBlood;
    private MaterialAutoCompleteTextView materialAutoCompleteTextViewNameHospital;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_add_new_history,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        materialAutoCompleteTextViewNameHospital = (MaterialAutoCompleteTextView) view.findViewById(R.id.tv_auto_completed_name_hospital);
        edtQualityUnitsDonation = (EditText)view.findViewById(R.id.edt_quality);
        spinnerUnitsDonationBlood = (MaterialBetterSpinner)view.findViewById(R.id.spinner_units_donation_blood);
        btnCancel = (Button)view.findViewById(R.id.btn_cancel);
        btnDone = (Button)view.findViewById(R.id.btn_done);
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
        String[] listNameHospital = getResources().getStringArray(R.array.name_hospital);
        ArrayAdapter<String> adapterUnitDonationBlood = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listUnitsDonationBlood);
        ArrayAdapter<String> adapterNameHospital = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listNameHospital);

        spinnerUnitsDonationBlood.setAdapter(adapterUnitDonationBlood);
        materialAutoCompleteTextViewNameHospital.setAdapter(adapterNameHospital);
    }


}
