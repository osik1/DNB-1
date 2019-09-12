package com.i_project.dnb.Constractors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.i_project.dnb.Adapters.DnbILibraryAdapter;
import com.i_project.dnb.R;


import java.util.ArrayList;

public class DnbILibraryConstructor extends ArrayAdapter< DnbILibraryAdapter >  implements Filterable {

    public DnbILibraryConstructor(Context context, ArrayList< DnbILibraryAdapter > listAdapters) {
        super(context, 0,listAdapters);
    }


    //========================================================//
    // this part checks if the views are used and if they are//
    // not in use, the views are than inflates so we can    //
    // reuse them                                          //
    //====================================================//
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;

        if(mView == null){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dnb_home_i_library,parent,false);
        }

        //=========================================================//
        // this part gets the positions of each items and add them//
        // to their respective views                             //
        //======================================================//
        DnbILibraryAdapter iLibraryAdapter = getItem(position);

        TextView book_title = mView.findViewById(R.id.book_title);
        TextView book_author = mView.findViewById(R.id.book_author);
        TextView book_version = mView.findViewById(R.id.book_version);
        TextView book_pub_date = mView.findViewById(R.id.book_pub_date);
        TextView download = mView.findViewById(R.id.download_link);
        ImageView imageView = mView.findViewById(R.id.book_download_button);

        book_author.setText(iLibraryAdapter.getBook_author());
        book_title.setText(iLibraryAdapter.getBook_title());
        book_version.setText(iLibraryAdapter.getBook_version());
        book_pub_date.setText(iLibraryAdapter.getBook_pub_date());
        download.setText(iLibraryAdapter.getDownload());
        imageView.setImageResource(iLibraryAdapter.getImage_download());

        return mView;
    }
}
