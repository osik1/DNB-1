package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dnb_ContactUs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("setJavaScriptEnabled")

    LinearLayout Instagram;
    LinearLayout Twitter;
    LinearLayout Facebook;
    LinearLayout Whatsapp;
    LinearLayout Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_home_contact_us);

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


        Instagram = findViewById(R.id.instagram);
        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openInstagram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/_u/iprojectleg/?hl=en"));
                startActivity(openInstagram);
            }
        });

        Whatsapp = findViewById(R.id.whatsapp);
        Whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String toNumber = "233209915005";
                    Intent sendWhatsapp = new Intent(Intent.ACTION_VIEW,Uri.parse("http://api.whatsapp.com/send?phone="+toNumber));
                    startActivity(sendWhatsapp);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Dnb_ContactUs.this,"Please install whatsapp ", Toast.LENGTH_LONG).show();
                }
            }
        });
        Twitter = findViewById(R.id.twitter);
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/IProjectLeg?s=08"));
                startActivity(openTwitter);
            }
        });

        Facebook = findViewById(R.id.facebook);
        Facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    // Check if FB app is even installed
                    Intent openFb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/pg/IProjectLeg/posts/?ref=page_internal"));
                    startActivity(openFb);
                } catch (Exception e) {
                    // Cache and Open a url in browser
                    Intent openFb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/IProjectLeg/posts/?ref=page_internal"));
                    startActivity(openFb);
                }
            }
        });

        Email = findViewById(R.id.email);
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Compile a Uri with the 'mailto' schema
                Intent openMail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "emalabi@st.ug.edu.gh", null));
                // Subject
                openMail.putExtra(Intent.EXTRA_SUBJECT, "");
                // Body of email
                openMail.putExtra(Intent.EXTRA_TEXT, "");
                // File attachment
                boolean attachedFileUri = false;
                openMail.putExtra(Intent.EXTRA_STREAM, attachedFileUri);
                // Check if the device has an email client
                if (openMail.resolveActivity(getPackageManager()) != null) {
                    // Prompt the user to select a mail app
                    startActivity(Intent.createChooser(openMail, "Choose your mail application"));
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_ContactUs.this, Dnb_FragmentActivity.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_ContactUs.this, Dnb_Announcement.class);
                    startActivity(a);
                    break;


                case R.id.nav_library:
                    Intent l = new Intent(Dnb_ContactUs.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;

                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_ContactUs.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

                case R.id.nav_profile:
                    Intent p = new Intent(Dnb_ContactUs.this, Dnb_Settings.class);
                    startActivity(p);
                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_ContactUs.this, Dnb_ContactUs.class);
                    startActivity(c);
                    break;

            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}
