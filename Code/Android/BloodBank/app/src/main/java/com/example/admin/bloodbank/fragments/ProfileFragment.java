package com.example.admin.bloodbank.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.activities.EditProfileActivity;
import com.example.admin.bloodbank.adapters.ProfileAdapter;
import com.example.admin.bloodbank.contraints.Contraint;
import com.example.admin.bloodbank.interfaces.ProfileListenerInterface;
import com.example.admin.bloodbank.objects.User;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Admin on 13/01/2017.
 */

public class ProfileFragment extends TemplateFragment {

    private RecyclerView recyclerView;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupRecyclerView();

    }

    public void setupRecyclerView() {
        String[] navNameMenu = getResources().getStringArray(R.array.array_item_profile);
        int[] itemIcons = {R.drawable.ic_count_donation_blood, R.drawable.ic_phone_profile, R.drawable.ic_mail, R.drawable.ic_address, R.drawable.ic_birthday, R.drawable.ic_gender};
        ProfileAdapter adapter = new ProfileAdapter(navNameMenu, itemIcons);
        adapter.setProfileListenner(new ProfileListenerInterface() {
            @Override
            public void onItemAvatarClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Contraint.RESULT_LOAD_IMAGE);
            }
            @Override
            public void onItemCallClick(View view, int position) {

            }

            @Override
            public void onItemMessageClick(View view, int position) {
            }

            @Override
            public void onItemEditProfileClick(View view, int position) { //action click btn edit profile
                User user = new User("1","2","user","saccerwin101@gmail.com","123456","Dang Duy Hau","24/10/1993","Nữ","01227949185","Đà Nẵng","Thanh Khê","...",0,"AB",false);
                Bundle bundle = new Bundle();
                bundle.putString(Contraint.PROFILE_FULLNAME, user.getFullName());
                bundle.putString(Contraint.PROFILE_PHONE, user.getPhone());
                bundle.putString(Contraint.PROFILE_DATEOFBIRTH, user.getDateOfBirth());
                bundle.putString(Contraint.PROFILE_GENDER, user.getGender());
                bundle.putString(Contraint.PROFILE_DISTICT, user.getDistrict());
                bundle.putString(Contraint.PROFILE_PASSWORD, user.getPassword());
                bundle.putString(Contraint.PROFILE_CITY, user.getCity());
                bundle.putString(Contraint.PROFILE_TYPEBLOOD, user.getType_blood());
                TemplateActivity.startActivityForResult(getActivity(), EditProfileActivity.class,0,bundle);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Contraint.RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            CircleImageView circleImageView = (CircleImageView)getView().findViewById(R.id.image_circle_avatar);
            circleImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
