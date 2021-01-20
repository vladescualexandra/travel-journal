package com.example.travel_journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.travel_journal.fragments.AboutFragment;
import com.example.travel_journal.fragments.ContactFragment;
import com.example.travel_journal.fragments.HomeFragment;
import com.example.travel_journal.fragments.ShareFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configNavigation();
        HomeFragment homeFragment = new HomeFragment();
        show(homeFragment);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_about:
                        fragment = new AboutFragment();
                        break;
                    case R.id.nav_contact:
                        fragment = new ContactFragment();
                        break;
                    default:
                        fragment = new ShareFragment();
                        break;
                }
                show(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        setNavigationView();
    }

    private void configNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
    }

    private void setNavigationView() {
        View header = navigationView.getHeaderView(0);
        TextView username = header.findViewById(R.id.nav_username);
        TextView email = header.findViewById(R.id.nav_email);

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.USER_PREFS, MODE_PRIVATE);
        String prefs_username = prefs.getString(RegisterActivity.USERNAME_KEY, null);
        String prefs_email = prefs.getString(RegisterActivity.EMAIL_KEY, null);

        if (prefs_username != null && prefs_email != null) {
            username.setText(prefs_username);
            email.setText(prefs_email);
        }
    }

    private void show(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame_container, fragment)
                    .commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }

}