package com.example.dbn;

import java.util.ArrayList;

public class Book {
    private String mTitle;
    private boolean nDownload;

    public Book(String mTitle, boolean nDownload) {
        this.mTitle = mTitle;
        this.nDownload = nDownload;
    }

    public String getmTitle() {
        return mTitle;
    }

    public boolean isnDownload() {
        return nDownload;
    }

    public static ArrayList<Book> createBooksList(int numBooks) {
        ArrayList<Book> books = new ArrayList<Book>();

        for (int i = 1; i <= numBooks; i++) {
            books.add(new Book("Call for Applications – H3Africa CEBioGen\n" +
                    "            MSc and PhD Scholarships " , i <= numBooks / 2));
        }

        return books;
    }
}
