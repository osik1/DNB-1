package com.example.dbn.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dbn.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFourHundredFragment extends Fragment {


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

        dnbRoot = inflater.inflate(R.layout.dnb_time_table_screen,container,false);

        return dnbRoot;
    }

}
