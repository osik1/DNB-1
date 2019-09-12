package com.i_project.dnb.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.i_project.dnb.Adapters.DnbGeneralAnnouncementAdapter;
import com.i_project.dnb.Utility.Dnb_generalAnnouncementUtility;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class Dnb_generalAnnouncementLoader extends AsyncTaskLoader {

    private String nUrl;

    public Dnb_generalAnnouncementLoader(Context context,String Url) {
        super( context );
        nUrl = Url;
    }

    @Override
    public List< DnbGeneralAnnouncementAdapter > loadInBackground() {
        //==========================================//
        // this part performs network call         //
        //========================================//

        List<DnbGeneralAnnouncementAdapter> newsItems = new ArrayList<>();
        if (nUrl == null) {
            return null;
        }
        try {
            newsItems = Dnb_generalAnnouncementUtility.fetchNewsItems( nUrl );
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItems;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
