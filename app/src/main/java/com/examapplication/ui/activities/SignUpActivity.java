package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends ParentActivity implements View.OnClickListener, ApiServiceCaller
{

    private Context mContext;
    private ImageView imgBack;
    private TextView txtContinue;
    private EditText edtFirstName, edtLastName, edtEmail, edtPhoneNo ,edtAddress, edtPassword, edtCnfPassword;
    private Spinner spinnerLocation;
    private Intent intent;
    private ArrayList<String> spinnerArrayName = new ArrayList<>();
    private ArrayList<String> spinnerArrayId = new ArrayList<>();
    private ArrayList<CategoryListModel> categoryListModels = new ArrayList<>();

    private String user = "", selectedCityId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContext = this;

        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        txtContinue = (TextView)findViewById(R.id.txt_continue);
        txtContinue.setOnClickListener(this);

        edtFirstName = (EditText)findViewById(R.id.edt_first_name);
        edtLastName = (EditText)findViewById(R.id.edt_last_name);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtPhoneNo = (EditText)findViewById(R.id.edt_phone_no);
        edtAddress = (EditText)findViewById(R.id.edt_address);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        edtCnfPassword = (EditText)findViewById(R.id.edt_cnf_password);

        spinnerLocation = (Spinner)findViewById(R.id.spinner_location);

        user = AppPreferences.getInstance(mContext).getString(AppConstants.USER, "");

        edtPhoneNo.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                validateNumber();
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                validateNumber();
            }
        });

        edtEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s)
            {}
        });

        edtPassword.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                validatePass();
            }

            @Override
            public void afterTextChanged(Editable s)
            {}
        });

    }

    private boolean validateNumber()
    {
        Pattern pattern;
        Matcher matcher;
        final String PHONE_PATTERN = "^[7-9][0-9]{9}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        String phone = edtPhoneNo.getText().toString().trim();
        matcher = pattern.matcher(phone);

        if (matcher.matches())
        {
            edtPhoneNo.setError(null);
            return true;
        }
        else
        {
            edtPhoneNo.setError("Enter valid mobile no");
            edtPhoneNo.requestFocus();
            return false;
        }
    }

    private boolean validateEmail()
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(edtEmail.getText().toString().trim());
        if (matcher.matches())
        {
            edtEmail.setError(null);
            return true;
        }
        else
        {
            edtEmail.setError("Enter valid email id");
            edtEmail.requestFocus();
            return false;
        }
    }

    private boolean validatePass()
    {
        if (edtPassword != null && edtPassword.length() >= 6)
        {
            return true;
        }
        else
        {
            edtPassword.setError("Password must be at least of 6 characters");
            edtPassword.requestFocus();
            return false;
        }
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
           if(edtFirstName.getText().toString().trim().equals(""))
           {
               Toast.makeText(mContext, getString(R.string.first_name_not_be_empty), Toast.LENGTH_SHORT).show();
           }
           else
           {
                if(edtLastName.getText().toString().trim().equals(""))
                {
                    Toast.makeText(mContext, getString(R.string.last_name_not_be_empty), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(edtEmail.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(mContext, getString(R.string.email_not_be_empty), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(!validateNumber())
                        {
                            Toast.makeText(mContext, getString(R.string.mobile_no_not_be_empty),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(edtAddress.getText().toString().trim().equals(""))
                            {
                                Toast.makeText(mContext, getString(R.string.address_not_be_empty),
                                        Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(edtPassword.getText().toString().trim().equals(""))
                                {
                                    Toast.makeText(mContext, getString(R.string.password_not_be_empty),
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if(edtCnfPassword.getText().toString().trim().equals(""))
                                    {
                                        Toast.makeText(mContext, getString(R.string.cnf_password_not_be_empty),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        if(edtPassword.getText().toString().trim().equals(
                                                edtCnfPassword.getText().toString().trim()))
                                        {
                                            saveUserDetails();
                                        }
                                        else
                                        {
                                            Toast.makeText(mContext, getString(R.string
                                                            .password_and_cnf_does_not_match),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
           }
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
                showLoadingDialog();
                JSONObject jsonObject = new JSONObject();

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_CITY_LIST_URL, ApiConstants.GET_CITY_LIST, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_CITY_LIST);

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

    private void saveUserDetails()
    {
        String fName = edtFirstName.getText().toString().trim();
        String lName = edtLastName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String mobile = edtPhoneNo.getText().toString().trim();
        String add = edtAddress.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();
        if (CommonUtility.getInstance(this).checkConnectivity(mContext))
        {
            try
            {
                showLoadingDialog();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("first_name", fName);
                jsonObject.put("last_name", lName);
                jsonObject.put("email", email);
                jsonObject.put("contact_no", mobile);
                jsonObject.put("address_line_1", add);
                jsonObject.put("city", selectedCityId);
                jsonObject.put("password", pass);
//                jsonObject.put("user_image", profileImage);

                String token = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.POST,
                        ApiConstants.SIGN_UP_URL, ApiConstants.SIGN_UP, this, token);
                App.getInstance().addToRequestQueue(request, ApiConstants.SIGN_UP);

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
            case ApiConstants.GET_CITY_LIST:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            dismissLoadingDialog();
                            categoryListModels.addAll(jsonResponse.city);
                            for(int i=0; i < categoryListModels.size(); i++)
                            {
                                spinnerArrayName.add(categoryListModels.get(i).getCityName());
                                spinnerArrayId.add(categoryListModels.get(i).getCityId());
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

            case ApiConstants.SIGN_UP:
            {
                if (jsonResponse != null)
                {
                    dismissLoadingDialog();
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            if(jsonResponse.responsedata != null) {
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_FIRST_NAME,
                                        jsonResponse.responsedata.getUserFirstName());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_LAST_NAME,
                                        jsonResponse.responsedata.getUserLastName());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_IMAGE,
                                        jsonResponse.responsedata.getUserImage());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_EMAIL,
                                        jsonResponse.responsedata.getEmailId());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_MOBILE,
                                        jsonResponse.responsedata.getMobileNo());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_ADDRESS,
                                        jsonResponse.responsedata.getAddress());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_STATE,
                                        jsonResponse.responsedata.getState());
                                AppPreferences.getInstance(mContext).putString(AppConstants.USER_CITY,
                                        jsonResponse.responsedata.getCity());
                                AppPreferences.getInstance(mContext).putString(AppConstants.TOKEN,
                                        jsonResponse.responsedata.getAuthorizationToken());

                                if(user.equals(getString(R.string.student))) {
                                    intent = new Intent(this, LandingStudentActivity.class);
                                }
                                else {
                                    intent = new Intent(this, LandingFacultyActivity.class);
                                }
                                startActivity(intent);
                                finish();
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Toast.makeText(mContext, jsonResponse.message, Toast.LENGTH_SHORT).show();
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
            case ApiConstants.GET_CITY_LIST:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
            case ApiConstants.SIGN_UP:
            {
//                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
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
            case ApiConstants.GET_CITY_LIST:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
            case ApiConstants.SIGN_UP:
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
                selectedCityId = cityId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {}

        });

    }
}
