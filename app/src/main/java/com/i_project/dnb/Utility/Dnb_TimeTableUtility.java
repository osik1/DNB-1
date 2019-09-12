package com.i_project.dnb.Utility;

import android.util.Log;

import com.i_project.dnb.Adapters.DnbTimeTableListAdapter;

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


public class Dnb_TimeTableUtility {
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
    private static String lecture_name;
    private static String course_start_time;
    private static String course_end_time;
    private static String course_venue;
    private static String course_title;
    private static String course_code;
    private static String current_date;



    public static String getnUrl() {
        Dnb_TimeTableUtility.nUrl = nUrl;

        return nUrl;
    }


    public static List< DnbTimeTableListAdapter > fetchNewsItems(String nUrlRequest) throws JSONException {

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

    private static List< DnbTimeTableListAdapter > parseJSON(String jsonResponds) throws JSONException {


        //==============================================//
        // this part set a new Array List              //
        //============================================//

        List< DnbTimeTableListAdapter > timeTableList = new ArrayList<>();

        //===============================================//
        //creating jsonobject and jsonArray             //
        //=============================================//


        try {
            JSONObject jsonObject = new JSONObject(jsonResponds);
            JSONArray jsonArray = jsonObject.getJSONArray("Sheet1");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = jsonArray.getJSONObject(i);

                course_code = jObject.getString("course_code");
                course_title = jObject.getString("course_title");
                course_venue = jObject.getString("course_venue");
                course_end_time = jObject.getString("course_end_time");
                course_start_time = jObject.getString("course_start_time");
                lecture_name = jObject.getString("lecture_name");
                current_date = jObject.getString("current_date");



                timeTableList.add(new DnbTimeTableListAdapter(course_code, course_title, course_start_time, course_end_time, lecture_name, course_venue, current_date));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeTableList;
    }

    public void myIntent() {
        List< DnbTimeTableListAdapter > newsArrayList = new ArrayList<>();
    }

}
