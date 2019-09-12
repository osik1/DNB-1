package com.i_project.dnb;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class DNB_SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // Need full screen without title bars for splash screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.dnb_splashscreen);
        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
        Thread t = new Thread(){
        };


    }
    private class LogoLauncher extends Thread{
        public void run(){

            try {

                Thread.sleep(2000);  // change the time according to your needs(its in milliseconds)


            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Intent i = new Intent(DNB_SplashScreen.this, Dnb_HomePage.class);     // change the activity you want to load
            startActivity(i);
            DNB_SplashScreen.this.finish();



        }
    }
}

