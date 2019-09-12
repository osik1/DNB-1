package com.i_project.dnb.Adapters;

public class DnbILibraryAdapter {
    //==============================================//
    // this is for the i-library                   //
    //============================================//
    private String book_title;
    private String book_author;
    private String book_pub_date;
    private String book_version;
    private String download;
    private int image_download;

    //============================================//
    // this is the constructor for the i-library //
    //==========================================//

    public DnbILibraryAdapter(String mBookTitle,String mBookAuthor,String mBookPubDate, String mBookVersion, String mDownload, int mImage_download){
        this.book_author = mBookAuthor;
        this.book_title = mBookTitle;
        this.book_pub_date = mBookPubDate;
        this.book_version= mBookVersion;
        this.download = mDownload;
        this.image_download = mImage_download;
    }



    //======================================================//
    // this part is for the I-library                      //
    //====================================================//
    public String getBook_author(){return book_author;}

    public String getBook_pub_date() {
        return book_pub_date;
    }

    public String getBook_version() {
        return book_version;
    }

    public String getDownload() {
        return download;
    }

    public String getBook_title() {
        return book_title;
    }

    public int getImage_download() {
        return image_download;
    }



}
