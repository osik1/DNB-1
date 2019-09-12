package com.i_project.dnb.Adapters;

public class DnbTimeTableListAdapter {

    //============================================//
    // this are the variable we will use for popu//
    //lation of the data                        //
    //=========================================//

    private String course_code;
    private String course_title;
    private String lecture_name;
    private String venue;
    private String start_time;
    private String end_time;
    private String current_date;

    //================================================//
    // this is the constructor for the timetable     //
    //==============================================//

    public DnbTimeTableListAdapter(String mCourseCode, String mCourseTitle, String mStartTime, String mEndTime, String mLectureName, String mVenue, String mCurrentTime) {
        this.course_code = mCourseCode;
        this.course_title = mCourseTitle;
        this.lecture_name = mLectureName;
        this.end_time = mEndTime;
        this.start_time = mStartTime;
        this.venue = mVenue;
        this.current_date = mCurrentTime;

    }


    //====================================================//
    // this part set the getter method for the adapter   //
    //==================================================//


    public String getCourse_code() {
        return course_code;
    }

    public String getCourse_title() {
        return course_title;
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

    public String getCurrent_date() {
        return current_date;
    }


}
