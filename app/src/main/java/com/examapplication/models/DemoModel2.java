package com.examapplication.models;

import java.util.ArrayList;

/**
 * Created by Piyush on 31-Jul-17.
 */

public class DemoModel2
{
    private String headerTitle;
    private ArrayList<DemoModel1> allItemsInSection;


    public DemoModel2() {

    }
    public DemoModel2(String headerTitle, ArrayList<DemoModel1> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<DemoModel1> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<DemoModel1> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

}
