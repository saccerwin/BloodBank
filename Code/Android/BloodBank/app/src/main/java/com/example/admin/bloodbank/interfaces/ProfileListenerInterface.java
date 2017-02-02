package com.example.admin.bloodbank.interfaces;

import android.view.View;

/**
 * Created by Admin on 02/02/2017.
 */

public interface ProfileListenerInterface {
    void onItemAvatarClick(View view, int position);
    void onItemCallClick(View view,int position);
    void onItemMessageClick(View view,int position);
    void onItemEditProfileLongClick(View view,int position);
}
