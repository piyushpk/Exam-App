package com.examapplication.webservices;


import com.examapplication.models.AddedExamModel;
import com.examapplication.models.CategoryListModel;
import com.examapplication.models.FacultyModel;
import com.examapplication.models.Model;
import com.examapplication.models.UserModel;

import java.util.ArrayList;

public class JsonResponse
{
    //Message and Success
    public String result = "success";
    public String MESSAGE = "message";
//    public String result = "result";
    public String message = "message";
    public static String FAILURE = "failure";
    public String authorization = "token";


    //ArrayList's Models
    public UserModel responsedata;
    public ArrayList<CategoryListModel> categories;
    public Model examdata;
    public Model coming_soon;
    public ArrayList<FacultyModel> faculties;
    public Model myexamorder;
    public Model myexamcomingorder;
    public Model submitteddata;
    public ArrayList<CategoryListModel> city;

    //List of String


}
