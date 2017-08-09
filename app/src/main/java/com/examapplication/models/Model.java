package com.examapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Piyush on 08-08-2017.
 * Bynry
 */
public class Model implements Serializable
{
    @SerializedName("page")
    String pageNo;

    @SerializedName("exam")
    ArrayList<RunningNowModel> runningExamList;

    @SerializedName("coming_page")
    String comingPageNo;

    @SerializedName("coming_exam")
    ArrayList<RunningNowModel> comingExamList;


    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public ArrayList<RunningNowModel> getRunningExamList() {
        return runningExamList;
    }

    public void setRunningExamList(ArrayList<RunningNowModel> runningExamList) {
        this.runningExamList = runningExamList;
    }

    public String getComingPageNo() {
        return comingPageNo;
    }

    public void setComingPageNo(String comingPageNo) {
        this.comingPageNo = comingPageNo;
    }

    public ArrayList<RunningNowModel> getComingExamList() {
        return comingExamList;
    }

    public void setComingExamList(ArrayList<RunningNowModel> comingExamList) {
        this.comingExamList = comingExamList;
    }
}