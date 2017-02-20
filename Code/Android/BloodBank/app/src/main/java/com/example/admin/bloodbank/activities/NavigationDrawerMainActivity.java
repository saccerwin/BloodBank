package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.adapters.NavigationRecyclerViewAdapter;
import com.example.admin.bloodbank.contraints.Contraint;
import com.example.admin.bloodbank.fragments.ClubJoinFragment;
import com.example.admin.bloodbank.fragments.HistoryDonationBloodFragment;
import com.example.admin.bloodbank.fragments.InstructionFragment;
import com.example.admin.bloodbank.fragments.ListClubFragment;
import com.example.admin.bloodbank.fragments.MainFragment;
import com.example.admin.bloodbank.fragments.ManagerClubFragment;
import com.example.admin.bloodbank.fragments.ProfileFragment;
import com.example.admin.bloodbank.fragments.SearchBloodGroupFragment;
import com.example.admin.bloodbank.fragments.StatisticalFragment;
import com.example.admin.bloodbank.managers.SPManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


public class NavigationDrawerMainActivity extends TemplateActivity {
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private NavigationRecyclerViewAdapter navigationRecyclerViewAdapter;
    private String[] listTitleUser;
    private String[] listTitleMember;
    private String[] listTitleAdmin;
    private TextView toobar_title;
    private int showMenuOptionId;
    private Menu menu;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseAuth auth;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_navigation_drawer);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toobar_title = (TextView) findViewById(R.id.toolbar_title);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());
        // get data from layout login and register
        auth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        fillDataSlidebarWithPermission();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        setupDrawerToggle();
//        ToastUtil.showLong(getContext(), auth.getCurrentUser().getDisplayName() + " / " + auth.getCurrentUser().getEmail() + "\n" + auth.getCurrentUser().getPhotoUrl());
        callFragment(new MainFragment());
        listTitleUser = getTitleMenuNav(R.array.navDrawerItemsUser);
        listTitleMember = getTitleMenuNav(R.array.navDrawerItemsMember);
        listTitleAdmin = getTitleMenuNav(R.array.navDrawerItemsAdmin);
        toobar_title.setText(R.string.home); // title home
        showMenuOptionId = 0;
    }
    private void fillDataSlidebarWithPermission() {
        if(auth.getCurrentUser().getDisplayName().isEmpty()) {
            mFirebaseDatabase.child(Contraint.FIREBASE_TREE_USER).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                        String avatar = userSnapshot.child(Contraint.FIREBASE_USERS_IMAGES).getValue().toString();
                        String fullname = userSnapshot.child(Contraint.PROFILE_FULLNAME).getValue().toString();
                        String email = auth.getCurrentUser().getEmail();
                        if(userSnapshot.child("email").getValue().equals(email)) {
                            // check decentralization with menu.
                            String permission = SPManager.getInstance(getContext()).getDecentralization();
                            if (!permission.isEmpty()) {
                                checkLogin(permission,fullname,avatar);
                                setupEventClickIntentItemMenu(permission);
                            } else {
                                isSignOut();
                            }
                            break;
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(Contraint.TAG, "onCancelled: " + databaseError);
                }
            });
        }
        else  {
            String permission = SPManager.getInstance(getContext()).getDecentralization();
            if (!permission.isEmpty()) {
                checkLogin(permission,auth.getCurrentUser().getDisplayName(),auth.getCurrentUser().getPhotoUrl().toString().trim());
                setupEventClickIntentItemMenu(permission);
            } else {
                isSignOut();
            }
        }
    }

    private String[] getTitleMenuNav(int id) {
        return getResources().getStringArray(id);
    }


    public void callFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.containerView, fragment);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {  // drawer close when button back active not out app
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.action_search, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.search_member).setVisible(false);
        return true;
    }

    public void showMenuSearch(int menuOptionId) { // check show Icon menu for fragment list club

        if (menuOptionId == 1) {
            menu.findItem(R.id.action_search).setVisible(true);
            menu.findItem(R.id.search_member).setVisible(false);
        } else if (menuOptionId == 3) {
            menu.findItem(R.id.action_search).setVisible(false);
            menu.findItem(R.id.search_member).setVisible(true);
        } else if (menuOptionId == 0) {
            menu.setGroupVisible(R.menu.action_search, false);
            menu.findItem(R.id.action_search).setVisible(false);
            menu.findItem(R.id.search_member).setVisible(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: {
                TemplateActivity.startActivity(this, FilterSearchClub.class, null);

            }
            break;
            case R.id.search_member: {
                TemplateActivity.startActivity(this, SearchMemberGroupActivity.class, null);
            }

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        return true;

    }


    private void setupDrawerToggle() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setTitle("");
    }


    private void setupEventClickIntentItemMenu(String decentralization) {

        if (decentralization.equals(Contraint.DECENTRALIZATION_ADMIN)) {
            navigationRecyclerViewAdapter.setmItemMenuNavClickListener(new NavigationRecyclerViewAdapter.OnItemMenuNavClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (position) {
                        case 1: {
                            callFragment(new MainFragment());
                            toobar_title.setText(listTitleAdmin[0]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 2: {
                            callFragment(new SearchBloodGroupFragment());
                            toobar_title.setText(listTitleAdmin[1]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 3: {
                            callFragment(new ListClubFragment());
                            toobar_title.setText(listTitleAdmin[2]);
                            showMenuOptionId = 1;
                        }
                        break;
                        case 4: {
                            callFragment(new ManagerClubFragment());
                            toobar_title.setText(listTitleAdmin[3]);
                            showMenuOptionId = 3;
                        }
                        break;
                        case 5: {
                            callFragment(new ProfileFragment());
                            toobar_title.setText(listTitleAdmin[4]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 6: {// menu history bloood
                            callFragment(new HistoryDonationBloodFragment());
                            toobar_title.setText(listTitleAdmin[5]);
                            showMenuOptionId = 0;

                        }
                        break;
                        case 7: {
                            callFragment(new StatisticalFragment());
                            toobar_title.setText(listTitleAdmin[6]);
                            showMenuOptionId = 0;

                        }
                        break;
                        case 8: {
                            toobar_title.setText(listTitleAdmin[7]);
                            callFragment(new InstructionFragment());
                            showMenuOptionId = 0;

                        }
                        break;
                        case 9: {
                            isSignOut();
                        }
                        break;
                        default: {
                            callFragment(new MainFragment());
                            showMenuOptionId = 0;
                        }
                        break;
                    }
                    drawerLayout.closeDrawers();
                    showMenuSearch(showMenuOptionId);
                }
            });
        }
        if (decentralization.equals(Contraint.DECENTRALIZATION_MEMBER)) {
            navigationRecyclerViewAdapter.setmItemMenuNavClickListener(new NavigationRecyclerViewAdapter.OnItemMenuNavClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (position) {
                        case 1: {
                            callFragment(new MainFragment());
                            toobar_title.setText(listTitleMember[0]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 2: {
                            callFragment(new SearchBloodGroupFragment());
                            toobar_title.setText(listTitleMember[1]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 3: {
                            callFragment(new ListClubFragment());
                            toobar_title.setText(listTitleMember[2]);
                            showMenuOptionId = 1;
                        }
                        break;
                        case 4: {
                            callFragment(new ClubJoinFragment());
                            toobar_title.setText(listTitleMember[3]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 5: {
                            callFragment(new ProfileFragment());
                            toobar_title.setText(listTitleMember[4]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 6: {// menu history blood
                            callFragment(new HistoryDonationBloodFragment());
                            toobar_title.setText(listTitleMember[5]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 7: {
                            toobar_title.setText(listTitleMember[6]);
                            callFragment(new InstructionFragment());
                            showMenuOptionId = 0;

                        }
                        break;
                        case 8: {
                            isSignOut();
                        }
                        break;
                        default: {
                            callFragment(new MainFragment());
                            showMenuOptionId = 0;
                        }
                        break;
                    }
                    drawerLayout.closeDrawers();
                    showMenuSearch(showMenuOptionId);
                }
            });
        }
        if (decentralization.equals(Contraint.DECENTRALIZATION_USER)) {
            navigationRecyclerViewAdapter.setmItemMenuNavClickListener(new NavigationRecyclerViewAdapter.OnItemMenuNavClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (position) {
                        case 1: {
                            callFragment(new MainFragment());
                            toobar_title.setText(listTitleUser[0]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 2: {
                            callFragment(new SearchBloodGroupFragment());
                            toobar_title.setText(listTitleUser[1]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 3: {
                            callFragment(new ListClubFragment());
                            toobar_title.setText(listTitleUser[2]);
                            showMenuOptionId = 1;
                        }
                        break;
                        case 4: {
                            callFragment(new ProfileFragment());
                            toobar_title.setText(listTitleUser[3]);
                            showMenuOptionId = 0;
                        }
                        break;
                        case 5: {
                            toobar_title.setText(listTitleUser[4]);
                            callFragment(new InstructionFragment());
                            showMenuOptionId = 0;

                        }
                        break;
                        case 6: { // logout
                            isSignOut();
                        }
                        break;

                    }
                    drawerLayout.closeDrawers();
                    showMenuSearch(showMenuOptionId);
                }
            });
        }


    }

    private void checkLogin(String permission,String fullname, String avatar) {
        String[] navNameMenuUser = getResources().getStringArray(R.array.navDrawerItemsUser);
        String[] navNameMenuMember = getResources().getStringArray(R.array.navDrawerItemsMember);
        String[] navNameMenuAdmin = getResources().getStringArray(R.array.navDrawerItemsAdmin);
        int[] iconNavMenuAdmin = {R.drawable.ic_home,
                R.drawable.ic_search_group_blood,
                R.drawable.ic_search_club,
                R.drawable.ic_manager_club,
                R.drawable.ic_profile,
                R.drawable.ic_history_donation_blood,
                R.drawable.ic_staticfic,
                R.drawable.ic_live_help_black_36dp,
                R.drawable.ic_logout};

        int[] iconNavMenuMember = {R.drawable.ic_home,
                R.drawable.ic_search_group_blood,
                R.drawable.ic_search_club,
                R.drawable.ic_manager_club,
                R.drawable.ic_profile,
                R.drawable.ic_history_donation_blood,
                R.drawable.ic_live_help_black_36dp,
                R.drawable.ic_logout};

        int[] iconNavMenuUser = {R.drawable.ic_home,
                R.drawable.ic_search_group_blood,
                R.drawable.ic_search_club,
                R.drawable.ic_profile,
                R.drawable.ic_live_help_black_36dp,
                R.drawable.ic_logout};


        switch (permission) {
            case Contraint.DECENTRALIZATION_USER: {
                navigationRecyclerViewAdapter = new NavigationRecyclerViewAdapter(navNameMenuUser, iconNavMenuUser, fullname, "Người dùng", avatar);

            }
            break;
            case Contraint.DECENTRALIZATION_MEMBER: {
                navigationRecyclerViewAdapter = new NavigationRecyclerViewAdapter(navNameMenuMember, iconNavMenuMember, fullname, "Thành viên", avatar);
            }
            break;
            case Contraint.DECENTRALIZATION_ADMIN: {
                navigationRecyclerViewAdapter = new NavigationRecyclerViewAdapter(navNameMenuAdmin, iconNavMenuAdmin, fullname, "Admin", avatar);
            }
            break;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        recyclerView.setAdapter(navigationRecyclerViewAdapter);
    }


    private void isSignOut() {
        Toast.makeText(getContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
        showMenuOptionId = 0;
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        TemplateActivity.startActivity(getContext(), LoginActivity.class, null);
        finish();
        SPManager.getInstance(getContext()).clear();
    }


}
