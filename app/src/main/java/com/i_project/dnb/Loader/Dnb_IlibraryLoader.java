package com.i_project.dnb.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.i_project.dnb.Adapters.DnbILibraryAdapter;
import com.i_project.dnb.Utility.Dnb_ILibraryUtility;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Dnb_IlibraryLoader extends AsyncTaskLoader {

    private String nUrl;

    public Dnb_IlibraryLoader(Context context, String Url) {
        super( context );
        nUrl = Url;

    }

    @Override
    public List< DnbILibraryAdapter > loadInBackground() {
        //==========================================//
        // this part performs network call         //
        //========================================//

        List<DnbILibraryAdapter> newsItems = new ArrayList<>();
        if (nUrl == null) {
            return null;
        }
        try {
            newsItems = Dnb_ILibraryUtility.fetchNewsItems( nUrl );
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
