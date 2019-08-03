package com.example.dbn;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import  android.support.v7.widget.Toolbar;
import android.os.Bundle;


import com.example.dbn.Adapters.DnbFragmentAdapter;

public class DnbFragmentActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnb_fragment);


        //======================================================//
        // this part configures the sliding tabs, finds the    //
        // pager that allow users to swipe between fragments  //
        // and create and adapter that shows which fragments //
        // should be shown on each page                     //
        //=================================================//

        ViewPager viewPager = findViewById( R.id.dnb_viewpager );
        TabLayout tabLayout = findViewById( R.id.dnb_tablayout );
        Toolbar toolbar = findViewById( R.id.tool_bar);

        DnbFragmentAdapter gagilFragmentAdapter = new DnbFragmentAdapter( this, getSupportFragmentManager());

        viewPager.setAdapter( gagilFragmentAdapter );
        tabLayout.setupWithViewPager( viewPager );


        //====================================================//
        // this part set the action bar for the timetable    //
        //==================================================//

        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    }
}
