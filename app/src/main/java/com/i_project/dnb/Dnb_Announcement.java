package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.i_project.dnb.Adapters.DnbGeneralAnnouncementAdapter;
import com.i_project.dnb.Constractors.DnbGeneralAnnouncementConstructor;
import com.i_project.dnb.Loader.Dnb_generalAnnouncementLoader;

import java.util.ArrayList;
import java.util.List;

public class Dnb_Announcement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks< List<DnbGeneralAnnouncementAdapter> > {

    private final int API_KEY = 6;
    private final String NEWS_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1vcJtKFlu67J-1NMBcHgtn7bC9Gq6aaaR5D7zQILkFO0&sheet=Sheet1";
    private ListView listView;
    private DnbGeneralAnnouncementConstructor announcementConstructor;
    private TextView annText;
    private ArrayList< DnbGeneralAnnouncementAdapter > listAdapters;

    private ProgressBar mLoadingProgressBar;


    SwipeRefreshLayout swipe;

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
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //Loads the website using the webView//
        swipe = findViewById(R.id.swipe_refresh);

        listAdapters = new ArrayList<>();
        listView = findViewById(R.id.dnb_general_ann_View);
        annText = findViewById(R.id.general_text_view);

        //================================================//
        // this part gets the system services using the  //
        // the connectivity manager, checks if there is //
        // internet connectivity to populate the news  //
        // and if no internet connectivity is not     //
        // found the system the user is notify       //
        //==========================================//

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        annText.setVisibility(View.VISIBLE);

        listView = findViewById(R.id.dnb_general_ann_View);
        annText = findViewById(R.id.general_text_view);
        mLoadingProgressBar = findViewById(R.id.pbLoading);

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader( API_KEY, null, this );
            annText.setVisibility(View.INVISIBLE);
            mLoadingProgressBar.setVisibility(View.VISIBLE);
        }
        else
            {
                annText.setVisibility(View.VISIBLE);
                annText.setText(R.string.connectionError);
                mLoadingProgressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            switch (id) {

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
    public Loader< List< DnbGeneralAnnouncementAdapter > > onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse( NEWS_URL );
        Uri.Builder buildUpon = uri.buildUpon();
        return new Dnb_generalAnnouncementLoader(this,buildUpon.toString());
    }

    @Override
    public void onLoadFinished(Loader< List< DnbGeneralAnnouncementAdapter > > loader, List< DnbGeneralAnnouncementAdapter > data) {

        listAdapters = new ArrayList<>(data);
        if (listAdapters.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder( this);
            builder.setMessage(R.string.connectionErrorMessage)
                    .setTitle( R.string.connectionError );
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        setList( listAdapters );
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader< List< DnbGeneralAnnouncementAdapter > > loader) {

        announcementConstructor.clear();

    }

    private void setList(ArrayList< DnbGeneralAnnouncementAdapter> listAdapters) {
        announcementConstructor = new DnbGeneralAnnouncementConstructor( this,listAdapters);
        listView.setAdapter(announcementConstructor);
    }
}
