package com.examapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Piyush on 13-08-2017.
 * Bynry
 */
public class NotificationModel implements Serializable
{
    @SerializedName("category")
    String categoryName;

    @SerializedName("id")
    String categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
