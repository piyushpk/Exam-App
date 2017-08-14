package com.examapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Piyush on 07-08-2017.
 * Bynry
 */
public class CategoryListModel implements Serializable
{
    @SerializedName("category")
    String categoryName;

    @SerializedName("id")
    String categoryId;

    @SerializedName("city_id")
    String cityId;

    @SerializedName("city_name")
    String cityName;

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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
