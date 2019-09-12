package com.i_project.dnb.Utility;

import android.util.Log;

import com.i_project.dnb.Adapters.DnbILibraryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Dnb_ILibraryUtility {

    //========================================//
    // this part states the error codes      //
    // to be used in the network connection //
    //=====================================//

    private static final int RESPONSE_CODE_OK = 200;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 10000;
    private static String nUrl;

    //=============================================//
    // list of strings to  be used for this task  //
    //===========================================//

    private static String book_title;
    private static String book_author;
    private static String book_pub_date;
    private static String book_version;
    private static String download;


    public static String getnUrl() {
        Dnb_ILibraryUtility.nUrl = nUrl;

        return nUrl;
    }


    public static List< DnbILibraryAdapter > fetchNewsItems(String nUrlRequest) throws JSONException {


        URL url = creatNewUrl(nUrlRequest);
        String JsonResponds = null;

        try {
            JsonResponds = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e("LOG_TAG", "there is an error", e);
        }
        return parseJSON(JsonResponds);
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String JsonResponds = "";

        if (url == null) {
            return JsonResponds;
        }
        //================================================//
        // this part defines the HttpResponds and check  //
        // for error handling                           //
        //=============================================//
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == RESPONSE_CODE_OK) {
                inputStream = httpURLConnection.getInputStream();
                JsonResponds = readInputStream(inputStream);
            } else {
                Log.e("LOG_TAG", "Error Responds Codes" + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("LOG_TAG", "There are issues with news items retrive from the jason", e);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        } else if (inputStream != null) {
            inputStream.close();
        }
        return JsonResponds;
    }

    //===================================================//
    // this reads the reads data from fetched           //
    // inputstream and return the result               //
    //================================================//
    private static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String newLine = bufferedReader.readLine();
            while (newLine != null) {
                stringBuilder.append(newLine);
                newLine = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    //==================================================//
    // this part creates a new URL object              //
    //================================================//
    private static URL creatNewUrl(String nUrlRequest) {
        URL url = null;
        try {
            url = new URL(nUrlRequest);
        } catch (MalformedURLException e) {
            Log.e("LOG_TAG", "There is a problem parshing the URL", e);
        }
        return url;
    }

    //=========================================================//
    // this part extract news list items and shows the        //
    // the responds                                          //
    //======================================================//

    private static List< DnbILibraryAdapter > parseJSON(String jsonResponds) throws JSONException {


        //==============================================//
        // this part set a new Array List              //
        //============================================//

        List< DnbILibraryAdapter > timeTableList = new ArrayList<>();

        //===============================================//
        //creating jsonobject and jsonArray             //
        //=============================================//


        try {
            JSONObject jsonObject = new JSONObject(jsonResponds);
            JSONArray jsonArray = jsonObject.getJSONArray("Sheet1");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = jsonArray.getJSONObject(i);

                book_title = jObject.getString("book_title");
                book_author = jObject.getString("book_author");
                book_pub_date = jObject.getString("book_pub_date");
                book_version = jObject.getString("book_version");
                download = jObject.getString("download");

               timeTableList.add(new DnbILibraryAdapter(book_title,book_author,book_pub_date,book_version,download));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeTableList;
    }


}
