package com.i_project.dnb;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.i_project.dnb.Adapters.DnbILibraryAdapter;
import com.i_project.dnb.Constractors.DnbILibraryConstructor;
import com.i_project.dnb.Loader.Dnb_IlibraryLoader;

import java.util.ArrayList;
import java.util.List;

public class Dnb_I_Library extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<List<DnbILibraryAdapter>>, AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    private final int API_KEY = 5;
    private final String NEWS_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1dHbgbcvUcgFsluwlIdL9gi4bN-FUWqLODhxx4j-ct0Y&sheet=Sheet1";
    private ListView listView;
    private DnbILibraryConstructor iLibraryConstructor;
    private TextView newsTextView;
    private ArrayList< DnbILibraryAdapter > listAdapters;

    private ProgressBar mLoadingProgressBar;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_home_ilibrary_listitem);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listAdapters = new ArrayList<>();
        listView = findViewById(R.id.dnb_ilibrary_View);
        newsTextView = findViewById(R.id.text_view);
        mLoadingProgressBar = findViewById(R.id.pbLoading);

        //Handles the Navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //================================================//
        // this part gets the system services using the  //
        // the connectivity manager, checks if there is //
        // internet connectivity to populate the news  //
        // and if no internet connectivity is not     //
        // found the system the user is notify       //
        //==========================================//

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        listView = findViewById(R.id.dnb_ilibrary_View);
        newsTextView = findViewById(R.id.ilibrary_text_view);
        mLoadingProgressBar = findViewById(R.id.pbLoading);

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader( API_KEY, null, this );
            mLoadingProgressBar.setVisibility(View.VISIBLE);
            newsTextView.setVisibility(View.INVISIBLE);
        }
        else {
            newsTextView.setVisibility( View.VISIBLE );
            newsTextView.setText( R.string.connectionError );
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
        }
        listView.setOnItemClickListener( this );

    }

    @Override
    public Loader< List< DnbILibraryAdapter > > onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse( NEWS_URL );
        Uri.Builder buildUpon = uri.buildUpon();
        return new Dnb_IlibraryLoader(this,buildUpon.toString());
    }

    @Override
    public void onLoadFinished(Loader< List< DnbILibraryAdapter > > loader, List< DnbILibraryAdapter > data) {
        listAdapters = new ArrayList<>(data);
        if (listAdapters.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder( this);
            builder.setMessage( R.string.connectionErrorMessage )
                    .setTitle( R.string.connectionError);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        setList( listAdapters );
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader< List< DnbILibraryAdapter > > loader) {
        iLibraryConstructor.clear();
    }

    private void setList(ArrayList< DnbILibraryAdapter> listAdapters) {
        iLibraryConstructor = new DnbILibraryConstructor( this,listAdapters);
        listView.setAdapter(iLibraryConstructor);
    }

    @Override
    public void onItemClick(AdapterView< ? > parent, View view, int position, long id) {
        Intent intent = new Intent( getApplicationContext(), Dnb_Download.class );
        intent.putExtra( "URL", listAdapters.get( position).getDownload() );
        startActivity( intent );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ilibrary_menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        try {
            
        }

        catch (Exception e){

        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}