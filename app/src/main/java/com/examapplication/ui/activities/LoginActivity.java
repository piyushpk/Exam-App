package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.utility.AppConstants;

public class LoginActivity extends ParentActivity implements View.OnClickListener
{

    private Context mContext;
    private ImageView imgLogo, imgBack;
    private Toolbar toolbar;
    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private TextView txtSignUp;

    private String btnName = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        imgLogo = (ImageView)findViewById(R.id.img_logo);
        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        edtUserName = (EditText)findViewById(R.id.edt_user_name);
        edtPassword = (EditText)findViewById(R.id.edt_password);

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        txtSignUp = (TextView)findViewById(R.id.txt_sign_up);
        txtSignUp.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(AppConstants.SEND_TO_LOGIN);
        if(bundle != null)
        {
            btnName = bundle.getString(AppConstants.BUTTON_NAME);
        }

    }

    @Override
    public void onClick(View v)
    {
        if(v == imgBack)
        {
            finish();
        }

        if(v == btnLogin)
        {
            intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
        }

        if(v == txtSignUp)
        {
            intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}
