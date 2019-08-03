package com.example.dbn.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dbn.Adapters.DnbTimeTableListAdapter;
import com.example.dbn.Constractors.DnbDeptUpdateConstructor;
import com.example.dbn.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentUpdateFragment extends Fragment {


    public DepartmentUpdateFragment() {
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

        ArrayList<DnbTimeTableListAdapter>list = new ArrayList<>();

        list.add(new DnbTimeTableListAdapter("Change on Sakia","kadaldkadladla dlladfkdfdkafakdfkfa" +
                "dfafafdaf;afadflald;aflakfjafewilsf"));

        ListView listView = dnbRoot.findViewById(R.id.dnb_list_View);
        DnbDeptUpdateConstructor constructor = new DnbDeptUpdateConstructor(getContext(),list);
        listView.setAdapter(constructor);

        return dnbRoot;
    }

}
