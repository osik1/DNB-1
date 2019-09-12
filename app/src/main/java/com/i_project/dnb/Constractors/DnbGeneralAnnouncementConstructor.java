package com.i_project.dnb.Constractors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.i_project.dnb.Adapters.DnbGeneralAnnouncementAdapter;
import com.i_project.dnb.R;

import java.util.ArrayList;

public class DnbGeneralAnnouncementConstructor extends ArrayAdapter< DnbGeneralAnnouncementAdapter > {


    public DnbGeneralAnnouncementConstructor( Context context, ArrayList< DnbGeneralAnnouncementAdapter > listAdapters) {
        super(context, 0, listAdapters);
    }

    //========================================================//
    // this part checks if the views are used and if they are//
    // not in use, the views are than inflates so we can    //
    // reuse them                                          //
    //====================================================//
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;

        if(mView ==null){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dnb_general_announcement_list_item, parent,false);
        }

        //=========================================================//
        // this part gets the positions of each items and add them//
        // to their respective views                             //
        //======================================================//
        DnbGeneralAnnouncementAdapter announcementAdapter = getItem(position);


        TextView message_title = mView.findViewById(R.id.ann_title);
        TextView ann_message = mView.findViewById(R.id.ann_details);
        TextView ann_date = mView.findViewById(R.id.ann_date);


        message_title.setText(announcementAdapter.getMessage_title());
        ann_message.setText(announcementAdapter.getGeneral_message());
        ann_date.setText(announcementAdapter.getGeneral_date());

        return mView;
    }
}
