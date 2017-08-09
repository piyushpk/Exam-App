package com.examapplication.webservices;


import com.examapplication.models.AddedExamModel;
import com.examapplication.models.CategoryListModel;
import com.examapplication.models.Model;
import com.examapplication.models.UserModel;

import java.util.ArrayList;

public class JsonResponse
{
    //Message and Success
    public String SUCCESS = "success";
    public String MESSAGE = "message";
    public String result;
    public static String FAILURE = "failure";
    public String authorization = "token";


    //ArrayList's Models
    public UserModel responsedata;
    public ArrayList<CategoryListModel> categories;
    public Model examdata;
    public Model coming_soon;

    //List of String


}
