package com.i_project.dnb;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Dnb_UGWebsite extends AppCompatActivity {

    WebView webView;
    SwipeRefreshLayout swipe;

    @SuppressLint("setJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnb_content_ug_website);


        //Loads the website using the webView//

        swipe = findViewById(R.id.swipe_refresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
            }
        });
        LoadWeb();
    }

    public void LoadWeb(){
        //Handles the webView
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("https://www.ug.edu.gh/");
        swipe.setRefreshing(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            //Handles SSL errors when received
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error){
                final AlertDialog.Builder builder = new AlertDialog.Builder(Dnb_UGWebsite.this);
                builder
                        .setMessage(R.string.notification_error_ssl_cert_invalid);
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }
            //Handles swipe to refresh page when loading
            public void onPageFinished(WebView view, String url){
                swipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //handles webview back pressed
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
