package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examapplication.R;
import com.examapplication.interfaces.ApiServiceCaller;
import com.examapplication.models.CategoryListModel;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends ParentActivity implements View.OnClickListener, ApiServiceCaller
{

    private Context mContext;
    private ImageView imgLogo, imgBack;
    private Toolbar toolbar;
    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private TextView txtSignUp;

    private String btnName = "", userName = "", password = "";
    private Intent intent;
    private ArrayList<CategoryListModel> categoryListModels = new ArrayList<>();

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
            /*userName = edtUserName.getText().toString().trim();
            password = edtPassword.getText().toString().trim();

            if(userName.isEmpty() || userName.equals(null) || userName.equals(""))
            {
                Toast.makeText(mContext, getString(R.string.please_add_user_name), Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(password.isEmpty() || password.equals(null) || password.equals(""))
                {
                    Toast.makeText(mContext, getString(R.string.please_add_password), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    callApiLogin(btnName);
                }
            }*/

            if(btnName.equals(getString(R.string.student)))
            {
                intent = new Intent(this, LandingStudentActivity.class);
            }
            else
            {
                intent = new Intent(this, LandingFacultyActivity.class);
            }
            startActivity(intent);
        }

        if(v == txtSignUp)
        {
            intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    private void callApiLogin(String type)
    {
        if (CommonUtility.getInstance(this).checkConnectivity(mContext))
        {
            try
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", userName);
                jsonObject.put("password", password);
                jsonObject.put("type", type);

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.POST,
                        ApiConstants.LOGIN_URL, ApiConstants.LOGIN, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.LOGIN);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(mContext, AppConstants.INTERNET_FAILED, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAsyncSuccess(JsonResponse jsonResponse, String label)
    {
        switch (label)
        {
            case ApiConstants.LOGIN:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            if(btnName.equals(getString(R.string.student)))
                            {
                                intent = new Intent(this, LandingStudentActivity.class);
                            }
                            else
                            {
                                intent = new Intent(this, LandingFacultyActivity.class);
                            }
                            startActivity(intent);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        if (jsonResponse.result != null && jsonResponse.result.equals(JsonResponse.FAILURE))
                        {
                            Toast.makeText(mContext, getString(R.string.user_name_and_password_does_not_match),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onAsyncFail(String message, String label, NetworkResponse response)
    {
        switch (label)
        {
            case ApiConstants.LOGIN:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onAsyncCompletelyFail(String message, String label)
    {
        switch (label)
        {
            case ApiConstants.LOGIN:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
