package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.examapplication.utility.AppPreferences;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class SignUpActivity extends ParentActivity implements View.OnClickListener, ApiServiceCaller
{

    private Context mContext;
    private ImageView imgBack;
    private TextView txtSignUp, txtContinue;
    private EditText edtFirstName, edtLastName, edtEmail, edtPhoneNo;
    private Spinner spinnerLocation;
    private Intent intent;
    private ArrayList<String> spinnerArrayName = new ArrayList<>();
    private ArrayList<String> spinnerArrayId = new ArrayList<>();
    private ArrayList<CategoryListModel> categoryListModels = new ArrayList<>();

    private String user = "";

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

        edtFirstName = (EditText)findViewById(R.id.edt_first_name);
        edtLastName = (EditText)findViewById(R.id.edt_last_name);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtPhoneNo = (EditText)findViewById(R.id.edt_phone_no);

        spinnerLocation = (Spinner)findViewById(R.id.spinner_location);

        user = AppPreferences.getInstance(mContext).getString(AppConstants.USER, "");

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
            if(user.equals(getString(R.string.student)))
            {
                intent = new Intent(this, LandingStudentActivity.class);
            }
            else
            {
                intent = new Intent(this, LandingFacultyActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getCityList();
    }

    private void getCityList()
    {
        if (CommonUtility.getInstance(this).checkConnectivity(mContext))
        {
            try
            {
                JSONObject jsonObject = new JSONObject();

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_CATEGORY_URL, ApiConstants.GET_CATEGORY, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_CATEGORY);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(mContext, getString(R.string.internet_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAsyncSuccess(JsonResponse jsonResponse, String label)
    {
        switch (label)
        {
            case ApiConstants.GET_CATEGORY:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            categoryListModels.addAll(jsonResponse.categories);
                            for(int i=0; i < categoryListModels.size(); i++)
                            {
                                spinnerArrayName.add(categoryListModels.get(i).getCategoryName());
                                spinnerArrayId.add(categoryListModels.get(i).getCategoryId());
                            }
                            spinnerList(spinnerArrayName, spinnerArrayId);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
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
        dismissLoadingDialog();
        switch (label)
        {
            case ApiConstants.GET_CATEGORY:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onAsyncCompletelyFail(String message, String label)
    {
        dismissLoadingDialog();
        switch (label)
        {
            case ApiConstants.GET_CATEGORY:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    public void spinnerList(final ArrayList<String> cityName, final ArrayList<String> cityId)
    {
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, cityName)
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(CommonUtility.getColor(getContext(), R.color.colorWhite));
                ((TextView) v).setTextSize(15f);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTextSize(15f);
                return v;
            }
        };
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapterLocation);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position,
                    long id)
            {
                String item = cityId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {}

        });

    }
}
