package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.examapplication.R;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;

public class SplashActivity extends ParentActivity
{

    private Context mContext;
    private AppPreferences preferences;
    private final int SPLASH_TIME_OUT = 3000;
    private ImageView imageView;
    private String userToken = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;
        preferences = AppPreferences.getInstance(this);

        imageView = (ImageView) findViewById(R.id.imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callNextScreen();
            }
        }, SPLASH_TIME_OUT);
        userToken = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");

    }

    private void callNextScreen()
    {
        if(userToken.equals(""))
            intent = new Intent(this, SendToLoginActivity.class);
        else
            intent = new Intent(this, LandingStudentActivity.class);

        startActivity(intent);
        this.finish();
    }
}