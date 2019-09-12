package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Dnb_UGLibrary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    SwipeRefreshLayout swipe;

    @SuppressLint("setJavaScriptEnabledd")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_home_ug_library);
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


        //Loads the website using the webView//

        swipe = findViewById(R.id.swipe_refresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
            }
        });
        LoadWeb();
    }

    public void LoadWeb(){
        //Handles the webView
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("http://balme.ug.edu.gh/");
        swipe.setRefreshing(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            //Handles SSL errors when received
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error){
                final AlertDialog.Builder builder = new AlertDialog.Builder(Dnb_UGLibrary.this);
                builder
                        .setMessage(R.string.notification_error_ssl_cert_invalid);
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }
            //Handles swipe to refresh page when loading
            public void onPageFinished(WebView view, String url){
                swipe.setRefreshing(false);
            }
        });
    }


    @Override
    public void onBackPressed() {
        //handles webview back pressed
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

//    @Override
//    protected void onStop() {
//        finish();
//        super.onStop();
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_UGLibrary.this, Dnb_FragmentActivity.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_UGLibrary.this, Dnb_UGLibrary.class);
                    startActivity(a);
                    break;

                case R.id.nav_library:
                    Intent l = new Intent(Dnb_UGLibrary.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;

                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_UGLibrary.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

                case R.id.nav_profile:
                    Intent p = new Intent(Dnb_UGLibrary.this, Dnb_Settings.class);
                    startActivity(p);
                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_UGLibrary.this, Dnb_ContactUs.class);
                    startActivity(c);
                    break;
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}
