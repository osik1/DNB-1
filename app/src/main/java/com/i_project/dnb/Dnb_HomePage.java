package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Dnb_HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("setJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Handles the Navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View timetable = findViewById(R.id.timeTable);
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTable();
            }
        });

        View featuredLink = findViewById(R.id.featuredLink);
        featuredLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featuredLink();
            }
        });

        final View generalAnn = findViewById(R.id.generalAnn);
        generalAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generalAnn();
            }
        });

    }

    public void timeTable(){
        //Links the timetable tab to the homepage//
        Intent intent = new Intent(this, Dnb_FragmentActivity.class);
        startActivity(intent);
    }

    public void featuredLink(){
        //Links the featured links tab to the homepage//
        Intent intent = new Intent(this, Dnb_FeaturedLink.class);
        startActivity(intent);
    }

    public void generalAnn(){
        //Links the general announcement tab to the homepage//
        Intent intent = new Intent(this, Dnb_Announcement.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_HomePage.this, Dnb_FragmentActivity.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_HomePage.this, Dnb_Announcement.class);
                    startActivity(a);
                    break;

                case R.id.nav_library:
                    Intent l = new Intent(Dnb_HomePage.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;

                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_HomePage.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

                case R.id.nav_profile:
                    Intent p = new Intent(Dnb_HomePage.this, Dnb_Settings.class);
                    startActivity(p);
                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_HomePage.this, Dnb_ContactUs.class);
                    startActivity(c);
                    break;

            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
