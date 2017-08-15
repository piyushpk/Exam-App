package com.examapplication.webservices;


public class ApiConstants
{

    public static final String DOMAIN_URL = "http://192.168.1.13:8987"; //  Mayur

//    public static final String DOMAIN_URL = "http://192.168.1.47:9999"; //  Sirji


    public static final String BASE_URL = DOMAIN_URL+"/api/";

    public static final int LOG_STATUS = 0; // TODO Please Make Sure that While creating signed APK make this value to "1"

    public static final String USER_ID = "user_id";
    public static final String DEVICE_TOKEN = "device_token";
    public static final String DEVICE_TYPE = "device_type";

    public static final String LOGIN_URL = BASE_URL + "student-login/";
    public static final String GET_CATEGORY_URL = BASE_URL + "categories/";
    public static final String GET_RUNNING_EXAM_LIST_URL = BASE_URL + "exams/";
    public static final String GET_COMING_EXAM_LIST_URL = BASE_URL + "coming-soon-exam/";
    public static final String GET_FACULTY_URL = BASE_URL + "faculties/";
    public static final String GET_MY_SUBMITTED_EXAM_STUDENT_URL = BASE_URL + "my-submitted-exam/";
    public static final String GET_MY_RUNNING_EXAM_URL = BASE_URL + "my-running-exam/";
    public static final String GET_MY_COMING_EXAM_URL = BASE_URL + "my-comingsoon-exam/";
    public static final String GET_CITY_LIST_URL = BASE_URL + "get-city/";
    public static final String UPDATE_PROFILE_URL = BASE_URL + "update-profile/";
    public static final String SIGN_UP_URL = BASE_URL + "student-signup/";
    public static final String GET_QUESTION_PAPER_URL = BASE_URL + "";



    //For Volley
    public static final String LOGIN = "1";
    public static final String GET_CATEGORY = "2";
    public static final String GET_RUNNING_EXAM_LIST = "3";
    public static final String GET_COMING_EXAM_LIST = "4";
    public static final String GET_FACULTY = "5";
    public static final String GET_MY_SUBMITTED_EXAM_STUDENT = "6";
    public static final String GET_MY_RUNNING_EXAM = "7";
    public static final String GET_MY_COMING_EXAM = "8";
    public static final String GET_CITY_LIST = "9";
    public static final String UPDATE_PROFILE = "10";
    public static final String SIGN_UP = "11";
    public static final String GET_QUESTION_PAPER = "12";



}
