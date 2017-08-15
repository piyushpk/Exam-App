package com.examapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Piyush on 15-08-2017.
 * Bynry
 */
public class QuestionModel implements Serializable
{
    @SerializedName("total_time")
    String examTotalTime;

    public String getExamTotalTime() {
        return examTotalTime;
    }

    public void setExamTotalTime(String examTotalTime) {
        this.examTotalTime = examTotalTime;
    }
}
