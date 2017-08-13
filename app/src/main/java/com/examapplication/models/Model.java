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

    @SerializedName("order_exam")
    ArrayList<RunningNowModel> myRunningExam;

    @SerializedName("order_comming_exam")
    ArrayList<RunningNowModel> myComingExam;

    @SerializedName("submitted_exam")
    ArrayList<RunningNowModel> mySubmittedExam;

    @SerializedName("submitted_page")
    String submittedPageNo;


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

    public ArrayList<RunningNowModel> getMyRunningExam() {
        return myRunningExam;
    }

    public void setMyRunningExam(ArrayList<RunningNowModel> myRunningExam) {
        this.myRunningExam = myRunningExam;
    }

    public ArrayList<RunningNowModel> getMyComingExam() {
        return myComingExam;
    }

    public void setMyComingExam(ArrayList<RunningNowModel> myComingExam) {
        this.myComingExam = myComingExam;
    }

    public ArrayList<RunningNowModel> getMySubmittedExam() {
        return mySubmittedExam;
    }

    public void setMySubmittedExam(ArrayList<RunningNowModel> mySubmittedExam) {
        this.mySubmittedExam = mySubmittedExam;
    }

    public String getSubmittedPageNo() {
        return submittedPageNo;
    }

    public void setSubmittedPageNo(String submittedPageNo) {
        this.submittedPageNo = submittedPageNo;
    }
}