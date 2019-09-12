package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Dnb_ContactUs extends AppCompatActivity {

    @SuppressLint("setJavaScriptEnabled")

    LinearLayout Instagram;
    LinearLayout Twitter;
    LinearLayout Facebook;
    LinearLayout Whatsapp;
    LinearLayout Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_content_contact_us);

        Instagram = findViewById(R.id.instagram);
        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openInstagram = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.instagram_link)));
                startActivity(openInstagram);
            }
        });

        Whatsapp = findViewById(R.id.whatsapp);
        Whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String toNumber = getString(R.string.whatsapp_number);
                    Intent sendWhatsapp = new Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.whatsapp_link)+toNumber));
                    startActivity(sendWhatsapp);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Dnb_ContactUs.this,getString(R.string.install_whatsapp), Toast.LENGTH_LONG).show();
                }
            }
        });
        Twitter = findViewById(R.id.twitter);
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.twitter_link)));
                startActivity(openTwitter);
            }
        });

        Facebook = findViewById(R.id.facebook);
        Facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    // Check if FB app is even installed
                    Intent openFb = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook_link)));
                    startActivity(openFb);
                } catch (Exception e) {
                    // Cache and Open a url in browser
                    Intent openFb = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook_link)));
                    startActivity(openFb);
                }
            }
        });
        //139916
        //pass 66769435957668

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
