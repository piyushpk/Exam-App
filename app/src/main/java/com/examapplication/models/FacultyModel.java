package com.examapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Piyush on 11-08-2017.
 * Bynry
 */
public class FacultyModel implements Serializable
{
    @SerializedName("teacher")
    String facultyName;

    @SerializedName("id")
    String facultyId;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
}