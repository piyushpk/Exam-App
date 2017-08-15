package com.examapplication.models;

import java.io.Serializable;

/**
 * Created by Piyush on 04-08-2017.
 * Bynry
 */
public class QuestionListModel implements Serializable
{

    public String question;
    public String subQuestion1, subQuestion2, subQuestion3, subQuestion4, subQuestion5, subQuestion6;



    public QuestionListModel()
    {}

    public QuestionListModel(String question, String subQue1, String subQue2, String subQue3, String subQue4, String subQue5,String subQue6)
    {
        this.question = question;
        this.subQuestion1 = subQue1;
        this.subQuestion2 = subQue2;
        this.subQuestion3 = subQue3;
        this.subQuestion4 = subQue4;
        this.subQuestion5 = subQue5;
        this.subQuestion6 = subQue6;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubQuestion1() {
        return subQuestion1;
    }

    public void setSubQuestion1(String subQuestion1) {
        this.subQuestion1 = subQuestion1;
    }

    public String getSubQuestion2() {
        return subQuestion2;
    }

    public void setSubQuestion2(String subQuestion2) {
        this.subQuestion2 = subQuestion2;
    }

    public String getSubQuestion3() {
        return subQuestion3;
    }

    public void setSubQuestion3(String subQuestion3) {
        this.subQuestion3 = subQuestion3;
    }

    public String getSubQuestion4() {
        return subQuestion4;
    }

    public void setSubQuestion4(String subQuestion4) {
        this.subQuestion4 = subQuestion4;
    }

    public String getSubQuestion5() {
        return subQuestion5;
    }

    public void setSubQuestion5(String subQuestion5) {
        this.subQuestion5 = subQuestion5;
    }

    public String getSubQuestion6() {
        return subQuestion6;
    }

    public void setSubQuestion6(String subQuestion6) {
        this.subQuestion6 = subQuestion6;
    }
}