package com.examapplication.models;

import java.io.Serializable;

/**
 * Created by Piyush on 08-08-2017.
 * Bynry
 */
public class SubmittedExamModel implements Serializable
{

    public String title;
    public String txt_content;
    public String txtName;
    public String txtCommentContent;



    public SubmittedExamModel()
    {}

    public SubmittedExamModel(String title, String content,String txtName,String txtCommentContent)
    {
        this.title = title;
        this.txt_content = content;
        this.txtName = txtName;
        this.txtCommentContent = txtCommentContent;
    }


}
