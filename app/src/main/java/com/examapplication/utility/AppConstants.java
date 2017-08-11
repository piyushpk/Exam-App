package com.examapplication.utility;

public class AppConstants
{

    // All static app constants are here except String , array of strings, color, dimentions etc

    public static final String SHARED_PREF = "mAppNameHere";

    public static String TEMP_DEVICE_GCM_TOKEN ="TEMP_DEVICE_TOKEN";
    public static String DEVICE_GCM_TOKEN ="DEVICE_TOKEN";
    public static String DEVICE_FCM_TOKEN ="DEVICE_FCM_TOKEN";
    public static final String IS_PERMISSION_DLG_SHOWING = "IS_PERMISSION_DLG_SHOWING";

    //Constant Integer Values

    public static final int GET_ACCOUNTS_PERMISSION = 1;
    public static final int PERMISSION_WRITE_STORAGE = 2;
    public static final int PERMISSION_FINE_LOCATION = 3;
    public static final int PERMISSION_READ_STORAGE = 4;
    public static final int PERMISSION_CAMERA = 5;
    public static int ALL_PERMISSIONS_RESULT = 55;

    //Constant for cropped image
    public static final String CROPPED_IMAGE = "croppedImage";
    public static final int IMAGE_CAPTURE = 103;
    public static final int SELECT_FILE = 107;
    public static String SHOWCASE_ID = "2";
    public static final String ID = "";
    public static final String UPLOAD_FOLDER = "/CityHoopla/Upload";
    public static final String VIDEO_PATH = UPLOAD_FOLDER + "/Video";
    public static final String IMAGE_PATH = UPLOAD_FOLDER + "/Image";

    public static final int CAPTURE_IMAGE = 1, SELECT_IMAGE = 2;
    public static final int PIC_CROP = 111;

    //Constant String Values

    public static String INTERNET_FAILED = "Please check your internet connection";
    public static String CHECK_PLAY_SERVICES = "Update Play Services";
    public static String API_FAIL_MESSAGE = "Something went wrong";

    //Constant Bundle values

    public static final String BUTTON_NAME = "button_name";
    public static final String SEND_TO_LOGIN = "send_to_login";
    public static final String USER = "user";
    public static final String VISIBLE_FRAG = "visible_frag";
    public static final String LANDING_STUDENT = "landing_student";

}
