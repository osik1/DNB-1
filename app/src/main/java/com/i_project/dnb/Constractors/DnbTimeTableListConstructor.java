package com.i_project.dnb.Constractors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.i_project.dnb.Adapters.DnbTimeTableListAdapter;
import com.i_project.dnb.R;

import java.util.ArrayList;


//======================================================//
// this part inherits properties from the Arrayadapter //
// making use of the timetable list adapter           //
//===================================================//
public class DnbTimeTableListConstructor extends ArrayAdapter<DnbTimeTableListAdapter> {


    //=======================================================//
    // this is constructor inherited from the arrayadapter  //
    //=====================================================//
    public DnbTimeTableListConstructor(Context context, ArrayList<DnbTimeTableListAdapter> listAdapters) {
        super(context, 0,listAdapters);
    }

    //========================================================//
    // this part checks if the views are used and if they are//
    // not in use, the views are than inflates so we can    //
    // reuse them                                          //
    //====================================================//
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View mView = convertView;

        if(mView ==null){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dnb_content_time_table_row
            ,parent,false);
        }

        //=========================================================//
        // this part gets the positions of each items and add them//
        // to their respective views                             //
        //======================================================//
        DnbTimeTableListAdapter tableListAdapter = getItem(position);

        TextView course_name = mView.findViewById(R.id.course_code);
        TextView course_venu = mView.findViewById(R.id.course_venue);
        TextView course_lecture = mView.findViewById(R.id.course_lecture);
        TextView course_time_end = mView.findViewById(R.id.course_time_end);
        TextView course_time_start = mView.findViewById(R.id.course_time_start);
        TextView course_title = mView.findViewById(R.id.course_title);
        TextView current_date = mView.findViewById(R.id.current_date);



        //=========================================================//
        // this part make use of the getter method from the time- //
        // tablelist adapter                                     //
        //======================================================//
        course_name.setText(tableListAdapter.getCourse_code());
        course_lecture.setText(tableListAdapter.getLecture_name());
        course_venu.setText(tableListAdapter.getVenue());
        course_time_end.setText(tableListAdapter.getEnd_time());
        course_time_start.setText(tableListAdapter.getStart_time());
        course_title.setText(tableListAdapter.getCourse_title());
        current_date.setText(tableListAdapter.getCurrent_date());

        return mView;
    }


}
