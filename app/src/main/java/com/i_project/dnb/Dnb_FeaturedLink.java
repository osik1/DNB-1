package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class Dnb_FeaturedLink extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("setJavaScriptEnabledd")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_home_featured_link);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Handles the Navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.nav_header));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        View card_ug_library = findViewById(R.id.card_ug_library);
        card_ug_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_ug_library();
            }
        });


        View card_sakai = findViewById(R.id.card_sakai);
        card_sakai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_sakai();
            }
        });

        View mis_web = findViewById(R.id.mis_web);
        mis_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mis_web();
            }
        });

        View card_ug_site = findViewById(R.id.card_ug_site);
        card_ug_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_ug_site();
            }
        });

        View card_i_library = findViewById(R.id.card_i_library);
        card_i_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_i_library();
            }
        });

        View card_student_service = findViewById(R.id.card_student_service);
        card_student_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_student_service();
            }
        });

    }

    public void card_ug_library(){
        //Links the timetable tab to the homepage//
        Intent intent = new Intent(this, Dnb_UGLibrary.class);
        startActivity(intent);
    }

    public void card_sakai(){
        //Links the featured links tab to the homepage//
        Intent intent = new Intent(this, Dnb_Sakai.class);
        startActivity(intent);
    }

    public void card_i_library(){
        //Links the featured links tab to the homepage//
        Intent intent = new Intent(this, Dnb_I_Library.class);
        startActivity(intent);
    }

    public void mis_web() {
        //Links the general announcement tab to the homepage//
        Intent intent = new Intent(this, Dnb_MISWeb.class);
        startActivity(intent);
    }

    public void card_ug_site(){
        //Links the general announcement tab to the homepage//
        Intent intent = new Intent(this, Dnb_UGWebsite.class);
        startActivity(intent);
    }

    public void card_student_service(){
        //Links the general announcement tab to the homepage//
        Intent intent = new Intent(this, Dnb_StudentService.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {

//                case R.id.nav_home:
//                    Intent h = new Intent(Dnb_FeaturedLink.this, Dnb_HomePage.class);
//                    startActivity(h);
//                    break;

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_FeaturedLink.this, Dnb_FragmentActivity.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_FeaturedLink.this, Dnb_Announcement.class);
                    startActivity(a);
                    break;


                case R.id.nav_library:
                    Intent l = new Intent(Dnb_FeaturedLink.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;

                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_FeaturedLink.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

//                case R.id.nav_settings:
//                    Intent p = new Intent(Dnb_FeaturedLink.this, Dnb_Settings.class);
//                    startActivity(p);
//                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_FeaturedLink.this, Dnb_ContactUs.class);
                    startActivity(c);
                    break;
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}