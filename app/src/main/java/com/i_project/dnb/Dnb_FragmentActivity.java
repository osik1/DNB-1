package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.i_project.dnb.Adapters.DnbFragmentAdapter;

public class Dnb_FragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @SuppressLint("setJavaScriptEnabledd")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_home_fragment);
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

        //======================================================//
        // this part configures the sliding tabs, finds the    //
        // pager that allow users to swipe between fragments  //
        // and create and adapter that shows which fragments //
        // should be shown on each page                     //
        //=================================================//

        ViewPager viewPager = findViewById( R.id.dnb_viewpager );

        TabLayout tabLayout = findViewById( R.id.dnb_tablayout );

        DnbFragmentAdapter gagilFragmentAdapter = new DnbFragmentAdapter( this, getSupportFragmentManager());

        viewPager.setAdapter( gagilFragmentAdapter );

        //====================================================//
        // this part set the action bar for the timetable    //
        //==================================================//

        toolbar.setTitle(getString(R.string.app_name));
        tabLayout.setupWithViewPager( viewPager );

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {
//
//                case R.id.nav_home:
//                    Intent h = new Intent(Dnb_FragmentActivity.this, Dnb_HomePage.class);
//                    startActivity(h);
//                    break;

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_FragmentActivity.this, Dnb_FragmentActivity.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_FragmentActivity.this, Dnb_Announcement.class);
                    startActivity(a);
                    break;

                case R.id.nav_library:
                    Intent l = new Intent(Dnb_FragmentActivity.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;

                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_FragmentActivity.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

//                case R.id.nav_settings:
//                    Intent p = new Intent(Dnb_FragmentActivity.this, Dnb_Settings.class);
//                    startActivity(p);
//                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_FragmentActivity.this, Dnb_ContactUs.class);
                    startActivity(c);
                    break;
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}
