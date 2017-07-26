package com.examapplication.interfaces;

import com.android.volley.NetworkResponse;
import com.examapplication.webservices.JsonResponse;


public interface ApiServiceCaller {
    void onAsyncSuccess(JsonResponse jsonResponse, String label);
    void onAsyncFail(String message, String label, NetworkResponse response);
    void onAsyncCompletelyFail(String message, String label);
}
