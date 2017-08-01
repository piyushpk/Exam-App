package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;

public class SignUpActivity extends ParentActivity implements View.OnClickListener
{

    private Context mContext;
    private ImageView imgBack;
    private TextView txtSignUp, txtContinue;
    private EditText edtName, edtEmail, edtPhoneNo, edtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContext = this;

        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        txtSignUp = (TextView)findViewById(R.id.txt_sign_up);
        txtContinue = (TextView)findViewById(R.id.txt_continue);
        txtContinue.setOnClickListener(this);

        edtName = (EditText)findViewById(R.id.edt_name);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtPhoneNo = (EditText)findViewById(R.id.edt_phone_no);
        edtAddress = (EditText)findViewById(R.id.edt_address);
    }

    @Override
    public void onClick(View v)
    {
        if(v == imgBack)
        {
            finish();
        }

        if(v == txtContinue)
        {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
