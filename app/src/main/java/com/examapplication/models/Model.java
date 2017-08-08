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
    ArrayList<RunningNowModel> examList;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public ArrayList<RunningNowModel> getExamList() {
        return examList;
    }

    public void setExamList(ArrayList<RunningNowModel> examList) {
        this.examList = examList;
    }
}