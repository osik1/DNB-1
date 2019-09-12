package com.i_project.dnb.Fragments;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.i_project.dnb.Adapters.DnbTimeTableListAdapter;
import com.i_project.dnb.Constractors.DnbTimeTableListConstructor;
import com.i_project.dnb.Loader.Dnb_TimeTableLoader;
import com.i_project.dnb.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFourHundredFragment extends Fragment implements LoaderManager.LoaderCallbacks< List< DnbTimeTableListAdapter > >{

    private final int API_KEY = 4;
    private final String NEWS_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1gplXHmNBR5XqYynY-BGngadwhSOVbpNOVM3Q8YDVFP8&sheet=Sheet1";
    private ListView listView;
    private DnbTimeTableListConstructor tableListConstructor;
    private TextView newsTextView;
    private ArrayList< DnbTimeTableListAdapter > listAdapters;

    private ProgressBar mLoadingProgressBar;

    public LevelFourHundredFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //=======================================================//
        // this part creates new fragment files to be added     //
        //=====================================================//
        View dnbRoot;

        dnbRoot = inflater.inflate(R.layout.dnb_content_time_table_screen,container,false);

        listAdapters = new ArrayList<>();
        listView = dnbRoot.findViewById(R.id.dnb_list_View);
        newsTextView = dnbRoot.findViewById(R.id.text_view);

        mLoadingProgressBar = dnbRoot.findViewById(R.id.pbLoading);


        //================================================//
        // this part gets the system services using the  //
        // the connectivity manager, checks if there is //
        // internet connectivity to populate the news  //
        // and if no internet connectivity is not     //
        // found the system the user is notify       //
        //==========================================//

        ConnectivityManager manager = (ConnectivityManager) getContext().getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        listView = dnbRoot.findViewById(R.id.dnb_list_View);
        newsTextView = dnbRoot.findViewById(R.id.text_view);
        mLoadingProgressBar.setVisibility(View.VISIBLE);

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader( API_KEY, null, this );
            newsTextView.setVisibility( View.INVISIBLE );
        }

        else {
            newsTextView.setVisibility( View.VISIBLE );
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
        }

        return dnbRoot;
    }

    @Override
    public Loader< List< DnbTimeTableListAdapter > > onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse( NEWS_URL );
        Uri.Builder buildUpon = uri.buildUpon();
        return new Dnb_TimeTableLoader(getContext(),buildUpon.toString());
    }

    @Override
    public void onLoadFinished(Loader< List< DnbTimeTableListAdapter > > loader, List< DnbTimeTableListAdapter > data) {
        listAdapters = new ArrayList<>(data);

        if (listAdapters.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
            builder.setTitle(R.string.connectionError);
            builder.setMessage(R.string.connectionMessage);
            builder.show();
        }
        setList( listAdapters );

        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader< List< DnbTimeTableListAdapter > > loader) {

        tableListConstructor.clear();
    }


    private void setList(ArrayList< DnbTimeTableListAdapter> listAdapters) {
        tableListConstructor = new DnbTimeTableListConstructor( getContext().getApplicationContext(), listAdapters );
        listView.setAdapter( tableListConstructor );
    }

}
