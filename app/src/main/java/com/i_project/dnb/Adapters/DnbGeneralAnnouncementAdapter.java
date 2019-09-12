package com.i_project.dnb.Adapters;

public class DnbGeneralAnnouncementAdapter {
    //==============================================//
    // this is for the general Announcement        //
    //============================================//
    private String message_title;
    private String general_message;
    private String general_date;


    //============================================//
    // this is the constructor for general_annou //
    //==========================================//

 public DnbGeneralAnnouncementAdapter(String mMessageTitle, String mGeneralMess, String mGeneralDate){

    this.message_title = mMessageTitle;
    this.general_message = mGeneralMess;
     this.general_date = mGeneralDate;
 }

    public String getGeneral_message() {
        return general_message;
    }

    public String getMessage_title() {
        return message_title;
    }

    public String getGeneral_date() {
        return general_date;
    }
}

