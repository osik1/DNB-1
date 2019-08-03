package com.example.dbn.Adapters;

public class DnbTimeTableListAdapter {

    //============================================//
    // this are the variable we will use for popu//
    //lation of the data                        //
    //=========================================//

    private String course_code;
    private String lecture_name;
    private String venue;
    private String start_time;
    private String end_time;
    private String dept_update_title;
    private String dept_update_message;


    //=================================================//
    // this is the constructor for the dept. update   //
    //===============================================//

    public DnbTimeTableListAdapter(String mDeptUPdateTitle, String mDaptUpdateMessage) {

        this.dept_update_title = mDeptUPdateTitle;
        this.dept_update_message = mDaptUpdateMessage;
    }

    //================================================//
    // this is the constructor for the timetable     //
    //==============================================//

    public DnbTimeTableListAdapter(String mCourseCode, String mStartTime, String mEndTime, String mLectureName, String mVenue) {
        this.course_code = mCourseCode;
        this.lecture_name = mLectureName;
        this.end_time = mEndTime;
        this.start_time = mStartTime;
        this.venue = mVenue;
    }



    //====================================================//
    // this part set the getter method for the adapter   //
    //==================================================//


    public String getCourse_code() {
        return course_code;
    }

    public String getDept_update_message() {
        return dept_update_message;
    }

    public String getDept_update_title() {
        return dept_update_title;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getLecture_name() {
        return lecture_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getVenue() {
        return venue;
    }
}
