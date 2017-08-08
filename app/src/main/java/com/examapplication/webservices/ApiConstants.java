package com.examapplication.webservices;


public class ApiConstants
{

    public static final String DOMAIN_URL = "http://192.168.1.13:8987"; //  UAT


    public static final String BASE_URL = DOMAIN_URL+"/api/";

    public static final int LOG_STATUS = 0; // TODO Please Make Sure that While creating signed APK make this value to "1"

    public static final String USER_ID = "user_id";
    public static final String DEVICE_TOKEN = "device_token";
    public static final String DEVICE_TYPE = "device_type";

    public static final String SIGNUP_URL = BASE_URL + "signup/";
    public static final String LOGIN_URL = BASE_URL + "student-login/";
    public static final String GET_CATEGORY_URL = BASE_URL + "categories/";
    public static final String GET_RUNNING_EXAM_LIST_URL = BASE_URL + "exams/";


    //For Volley
    public static final String LOGIN = "1";
    public static final String GET_CATEGORY = "2";
    public static final String GET_RUNNING_EXAM_LIST = "3";



}
