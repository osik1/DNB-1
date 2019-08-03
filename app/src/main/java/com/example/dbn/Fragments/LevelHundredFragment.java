package com.example.dbn.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dbn.Adapters.DnbTimeTableListAdapter;
import com.example.dbn.Constractors.DnbTimeTableListConstructor;
import com.example.dbn.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LevelHundredFragment extends Fragment {


    public LevelHundredFragment() {
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


        //==========================================================//
        // this part glues the data unto the arraylist             //"CSIT 202"
        //========================================================//

        ArrayList<DnbTimeTableListAdapter> listAdapters = new ArrayList<>();

        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));
        listAdapters.add(new DnbTimeTableListAdapter("CSIT 202","06:00","10:00", "Mr. Adingo","New Block A2"));


        //============================================================//
        // this part map the arrayadapter to our default constructor //
        //==========================================================//

        DnbTimeTableListConstructor listConstructor = new DnbTimeTableListConstructor(getContext(),listAdapters);
        ListView listView = dnbRoot.findViewById(R.id.dnb_list_View);
        listView.setAdapter(listConstructor);
        return dnbRoot;
    }


}
