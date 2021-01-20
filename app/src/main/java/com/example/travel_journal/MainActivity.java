package com.example.travel_journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        configNavigation();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
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

}