package com.examapplication.models;

/**
 * Created by Piyush on 31-Jul-17.
 */

public class DemoModel1
{
    private String name;
    private String url;
    private String description;


    public DemoModel1() {
    }

    public DemoModel1(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
