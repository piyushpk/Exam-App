package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;

public class SendToLoginActivity extends ParentActivity implements View.OnClickListener
{

    private Context mContext;
    private TextView txtLoginAs;
    private ImageView imgLogo;
    private Button btnFaculty, btnStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_login);

        mContext = this;
        txtLoginAs = (TextView)findViewById(R.id.txt_login_as);
        imgLogo = (ImageView)findViewById(R.id.img_logo);

        btnFaculty = (Button)findViewById(R.id.btn_faculty);
        btnFaculty.setOnClickListener(this);
        btnStudent = (Button)findViewById(R.id.btn_student);
        btnStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == btnFaculty)
        {
            canLoginScreen(getString(R.string.faculty));
        }

        if(v == btnStudent)
        {
            canLoginScreen(getString(R.string.student));
        }
    }

    private void canLoginScreen(String btnName)
    {
        AppPreferences.getInstance(mContext).putString(AppConstants.USER, btnName);
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.BUTTON_NAME, btnName);
        intent.putExtra(AppConstants.SEND_TO_LOGIN, bundle);
        startActivity(intent);
    }
}
