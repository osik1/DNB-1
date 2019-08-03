package com.example.dbn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>{

    // Store a member variable for the contacts
    private ArrayList<Book> mBooks;

    // Pass in the contact array into the constructor
    public BookRecyclerAdapter (ArrayList<Book> books) {
        mBooks = books;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.ilibrary_list_item, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Get the data model based on position
        Book book = mBooks.get(i);

        // Set item views based on your views and data model
        TextView textView = viewHolder.titleTextView;
        textView.setText(book.getmTitle());
        Button button = viewHolder.downloadButton;
        button.setText(book.isnDownload() ? "Download" : "Download");
        button.setEnabled(book.isnDownload());
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
             TextView titleTextView;
             Button downloadButton;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                titleTextView = (TextView) itemView.findViewById(R.id.title);
                downloadButton = (Button) itemView.findViewById(R.id.download_button);
            }
        }
    }



