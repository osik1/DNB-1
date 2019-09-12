package com.i_project.dnb.Fragments;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.i_project.dnb.Adapters.DnbILibraryAdapter;
import com.i_project.dnb.Constractors.DnbILibraryConstructor;
import com.i_project.dnb.Loader.Dnb_IlibraryLoader;
import com.i_project.dnb.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
class ILibraryFragment extends Fragment implements LoaderManager.LoaderCallbacks< List< DnbILibraryAdapter > >{


    private final int API_KEY = 5;
    private final String NEWS_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1dHbgbcvUcgFsluwlIdL9gi4bN-FUWqLODhxx4j-ct0Y&sheet=Sheet1";
    private ListView listView;
    private DnbILibraryConstructor iLibraryConstructor;
    private TextView newsTextView;
    private ArrayList< DnbILibraryAdapter > listAdapters;
    private ProgressBar mLoadingProgressBar;


    public ILibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View dnbview;

        dnbview = inflater.inflate(R.layout.dnb_ilibrary_listview,container,false);


        listAdapters = new ArrayList<>();
        newsTextView = dnbview.findViewById(R.id.text_view);
        mLoadingProgressBar = dnbview.findViewById(R.id.pbLoading);


        //================================================//
        // this part gets the system services using the  //
        // the connectivity manager, checks if there is //
        // internet connectivity to populate the news  //
        // and if no internet connectivity is not     //
        // found the system the user is notify       //
        //==========================================//

        ConnectivityManager manager = (ConnectivityManager) getContext().getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        listView = dnbview.findViewById(R.id.dnb_ilibrary_View);
        newsTextView = dnbview.findViewById(R.id.text_view);

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader( API_KEY, null, this );
            mLoadingProgressBar.setVisibility(View.VISIBLE);
        }else {
            newsTextView.setVisibility( View.VISIBLE );
            newsTextView.setText( R.string.no_internet_connection );
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
        }

        return dnbview;
    }


    @Override
    public Loader< List< DnbILibraryAdapter > > onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse( NEWS_URL );
        Uri.Builder buildUpon = uri.buildUpon();

        return new Dnb_IlibraryLoader(getContext(), buildUpon.toString());
    }

    @Override
    public void onLoadFinished(Loader< List< DnbILibraryAdapter > > loader, List< DnbILibraryAdapter > data) {

        listAdapters = new ArrayList<>(data);
        if (listAdapters.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
            builder.setMessage( R.string.connectionMessage)
                    .setTitle(R.string.connectionError);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        setList( listAdapters );
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    private void setList(ArrayList< DnbILibraryAdapter> listAdapters) {
        iLibraryConstructor = new DnbILibraryConstructor( getContext().getApplicationContext(), listAdapters );
        listView.setAdapter( iLibraryConstructor );
    }

    @Override
    public void onLoaderReset(Loader< List< DnbILibraryAdapter > > loader) {

        iLibraryConstructor.clear();
    }
}
