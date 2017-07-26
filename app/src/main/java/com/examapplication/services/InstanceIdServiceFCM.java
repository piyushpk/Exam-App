package com.examapplication.services;

import android.content.Context;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;



public class InstanceIdServiceFCM extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";
    private Context mContext;

    @Override
    public void onTokenRefresh() {
        mContext = this;
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
       // AppPreferences.getInstance(this).putString(AppConstants.SHOW_USER_GUIDE, "yes");
        Log.i(TAG, "RefreshedToken: " + refreshedToken);
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }

}
