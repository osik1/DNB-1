package com.i_project.dnb.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.i_project.dnb.Adapters.DnbTimeTableListAdapter;
import com.i_project.dnb.Utility.Dnb_TimeTableUtility;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Dnb_TimeTableLoader extends AsyncTaskLoader {

    private String nUrl;

    public Dnb_TimeTableLoader(Context context, String Url) {
        super( context );
        nUrl = Url;

    }

    @Override
    public List< DnbTimeTableListAdapter > loadInBackground() {
        //==========================================//
        // this part performs network call         //
        //========================================//

        List<DnbTimeTableListAdapter> newsItems = new ArrayList<>();
        if (nUrl == null) {
            return null;
        }
        try {
            newsItems = Dnb_TimeTableUtility.fetchNewsItems( nUrl );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItems;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
