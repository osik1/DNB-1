package com.example.dbn.Constractors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dbn.Adapters.DnbTimeTableListAdapter;
import com.example.dbn.R;

import java.util.ArrayList;

//====================================================//
// this is the constructor inherited from arraydapter//
// class and implementing on tablelist adapter      //
//=================================================//
public class DnbDeptUpdateConstructor extends ArrayAdapter<DnbTimeTableListAdapter> {

    //===================================================//
    // this constructor inherits from an array adapter  //
    //=================================================//
    public DnbDeptUpdateConstructor(Context context , ArrayList<DnbTimeTableListAdapter> listAdapters) {
        super(context, 0,listAdapters);
    }

    //==========================================================//
    // this part checks if a view is in use and if not it      //
    // inflate the view                                       //
    //=======================================================//

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;

        if (mView == null){
            mView = LayoutInflater.from(getContext()).inflate
                    (R.layout.dnb_dept_update,parent,false);
        }

        //=========================================================//
        // this part gets the positions of each items and add them//
        // to their respective views                             //
        //======================================================//
        DnbTimeTableListAdapter adapter = getItem(position);

        TextView dept_update_title = mView.findViewById(R.id.dnb_dept_update_title);
        TextView dept_update_message = mView.findViewById(R.id.dnb_dept_update_message);

        //=========================================================//
        // this part make use of the getter method from the time- //
        // tablelist adapter                                     //
        //======================================================//

        dept_update_title.setText(adapter.getDept_update_title());
        dept_update_message.setText(adapter.getDept_update_message());
        return mView;
    }
}
