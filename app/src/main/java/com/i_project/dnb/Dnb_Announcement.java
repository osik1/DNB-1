package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.i_project.dnb.Adapters.DnbGeneralAnnouncementAdapter;
import com.i_project.dnb.Constractors.DnbGeneralAnnouncementConstructor;
import com.i_project.dnb.Loader.Dnb_generalAnnouncementLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dnb_Announcement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<List<DnbGeneralAnnouncementAdapter>> {

    private final int API_KEY = 6;
    private final String NEWS_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1vcJtKFlu67J-1NMBcHgtn7bC9Gq6aaaR5D7zQILkFO0&sheet=Sheet1";
    private ListView listView;
    private DnbGeneralAnnouncementConstructor announcementConstructor;
    private TextView annText;
    private ArrayList<DnbGeneralAnnouncementAdapter> listAdapters;

    private ProgressBar mLoadingProgressBar;

    @SuppressLint("setJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_home_ann);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Handles the Navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.BLACK);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        listAdapters = new ArrayList<>();
        listView = findViewById(R.id.dnb_general_ann_listiew);

        //================================================//
        // this part gets the system services using the  //
        // the connectivity manager, checks if there is //
        // internet connectivity to populate the news  //
        // and if no internet connectivity is not     //
        // found the system the user is notify       //
        //==========================================//

        listView = findViewById(R.id.dnb_general_ann_listiew);
        mLoadingProgressBar = findViewById(R.id.pbLoading);

        if (!isConnected(Dnb_Announcement.this))
            buildDialog(Dnb_Announcement.this).show();
        else {
        }
    }

    public boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(API_KEY, null, this);
            android.net.NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi.isConnectedOrConnecting()))
                mLoadingProgressBar.setVisibility(View.VISIBLE);
            return true;
        } else {
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
            return false;
        }
    }

    public AlertDialog.Builder buildDialog(Context c) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(R.string.connectionError);
        builder.setMessage(R.string.connectionErrorMessage);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return builder;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {

//                case R.id.nav_home:
//                    Intent h = new Intent(Dnb_Announcement.this, Dnb_HomePage.class);
//                    startActivity(h);
//                    break;

                case R.id.nav_timetable:
                    Intent t = new Intent(Dnb_Announcement.this, Dnb_FragmentActivity.class);
                    startActivity(t);
                    break;

                case R.id.nav_announcement:
                    Intent a = new Intent(Dnb_Announcement.this, Dnb_Announcement.class);
                    startActivity(a);
                    break;

                case R.id.nav_library:
                    Intent l = new Intent(Dnb_Announcement.this, Dnb_I_Library.class);
                    startActivity(l);
                    break;

                case R.id.nav_featured_links:
                    Intent f = new Intent(Dnb_Announcement.this, Dnb_FeaturedLink.class);
                    startActivity(f);
                    break;

                case R.id.nav_contact:
                    Intent c = new Intent(Dnb_Announcement.this, Dnb_ContactUs.class);
                    startActivity(c);
                    break;

            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    @Override
    public Loader<List<DnbGeneralAnnouncementAdapter>> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(NEWS_URL);
        Uri.Builder buildUpon = uri.buildUpon();
        return new Dnb_generalAnnouncementLoader(this, buildUpon.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<DnbGeneralAnnouncementAdapter>> loader, List<DnbGeneralAnnouncementAdapter> data) {

        listAdapters = new ArrayList<>(data);
        if (listAdapters.isEmpty()) {
            buildDialog(Dnb_Announcement.this).show();
        }
        setList(listAdapters);
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<DnbGeneralAnnouncementAdapter>> loader) {

        announcementConstructor.clear();

    }

    private void setList(ArrayList<DnbGeneralAnnouncementAdapter> listAdapters) {
        announcementConstructor = new DnbGeneralAnnouncementConstructor(this, listAdapters);
        listView.setAdapter(announcementConstructor);
    }

}
