package com.i_project.dnb;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class Dnb_Download extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    Button button;
    DownloadManager downloadManager;
    long queueId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_activity);

        button = findViewById(R.id.book_download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if os is Marshmallow or above, handle runtime permission

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission denied, request it!
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

                        //show pop up for runtime permission
                        requestPermissions(permission, PERMISSION_STORAGE_CODE);
                    } else {
                        //permission granted, perform download
                        Download();
                    }
                } else {
                    //system os is less than marshmallow, perform download
                    Download();
                }
            }
        });

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    DownloadManager.Query req_query = new DownloadManager.Query();
                    req_query.setFilterById(queueId);

                    Cursor cursor = downloadManager.query(req_query);
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    }
                }
            }
        };
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private void Download() {

        Intent intent = getIntent();
        String string = intent.getStringExtra("URL");

        //get the filename and the extension from the download link
        String fileName = URLUtil.guessFileName(string, null, MimeTypeMap.getFileExtensionFromUrl(string));

        // check to see if the file already exist on the device or not
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/" + fileName);

        if (file.exists()) {
            //show a toast message if the file already exist
            Toast.makeText(getApplicationContext(), fileName + "already exist", Toast.LENGTH_LONG).show();
        }
        // Download the file if it does not exist on the device
        else {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(string));

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle(getString(R.string.downloading));
            request.setDescription(fileName);

            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, fileName);

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handles the back button navigation on the toolbar
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted from popup, perform download
                    Download();
                } else {
                    //permission denied from popup, show error message
                    Toast.makeText(this, "Permission denied ....!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}

