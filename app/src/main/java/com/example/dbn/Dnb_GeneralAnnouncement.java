package com.example.dbn;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Dnb_GeneralAnnouncement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    SwipeRefreshLayout swipe;

    @SuppressLint("setJavaScriptEnabledd")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_general_announcement);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Handles the Navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("https://andela.com/alc");
        swipe.setRefreshing(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            //Handles SSL errors when received
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error){
                final AlertDialog.Builder builder = new AlertDialog.Builder(Dnb_GeneralAnnouncement.this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_save; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Dnb_Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {
                case R.id.nav_home:
                    Intent h = new Intent(Dnb_GeneralAnnouncement.this, Dnb_Home.class);
                    startActivity(h);
                    break;

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_GeneralAnnouncement.this, Dnb_TimeTable.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_GeneralAnnouncement.this, Dnb_GeneralAnnouncement.class);
                    startActivity(a);
                    break;

                case R.id.nav_newsfeed:
                    Intent n = new Intent(Dnb_GeneralAnnouncement.this, Dnb_NewsFeed.class);
                    startActivity(n);
                    break;

                case R.id.nav_library:
                    Intent l = new Intent(Dnb_GeneralAnnouncement.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;
                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_GeneralAnnouncement.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

                case R.id.nav_profile:
                    Intent p = new Intent(Dnb_GeneralAnnouncement.this, Dnb_ProfileSettings.class);
                    startActivity(p);
                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_GeneralAnnouncement.this, Dnb_MessageUs.class);
                    startActivity(c);
                    break;

                case R.id.nav_logout:
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                    break;
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}

