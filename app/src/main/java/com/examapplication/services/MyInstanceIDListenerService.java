package com.examapplication.services;


public class MyInstanceIDListenerService extends InstanceIdServiceFCM {

    private static final String TAG = "MyInstanceIDLS";


    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
//        MMToast.getInstance().showShortToast(TAG,this);
    }
    // [END refresh_token]
}
