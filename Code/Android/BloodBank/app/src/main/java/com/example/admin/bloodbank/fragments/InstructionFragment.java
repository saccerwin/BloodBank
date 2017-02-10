package com.example.admin.bloodbank.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.activities.InstructionFeatureActivity;

/**
 * Created by Admin on 10/02/2017.
 */

public class InstructionFragment extends TemplateFragment {
    private Button btnInstruction;
    private WebView webViewIntroductionAbout;
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instruction_app,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        btnInstruction = (Button)view.findViewById(R.id.btn_instruction);
        webViewIntroductionAbout = (WebView)view.findViewById(R.id.web_view_about);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        showStaticContent();
        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemplateActivity.startActivity(getActivity(), InstructionFeatureActivity.class,null);
            }
        });

    }

    private void showStaticContent()  {
        WebSettings settings = webViewIntroductionAbout.getSettings();
        settings.setDefaultTextEncodingName("utf-8");

        String staticContent="<h2 style=\"color:#CD242A\"> Giới thiệu về app </h2>"
                + "<h3>App phục vụ chủ yếu cho cộng đồng máu nóng giúp các thành viên trong các câu lạc bộ có thể dê dàng tìm kiếm, giao tiếp với nhau</h3>"
                + "<ul><li>Tác giả: Đặng Duy Hậu</li>"
                + "<li>Lớp: 13CNTT</li>"
                +"<li>Trường: Đại Học Sư Phạm Đà Nẵng</li></ul>";
        webViewIntroductionAbout.loadData(staticContent, "text/html; charset=utf-8", "utf-8");
    }
}
