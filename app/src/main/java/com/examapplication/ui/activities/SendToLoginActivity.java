package com.examapplication.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examapplication.R;
import com.examapplication.interfaces.ApiServiceCaller;
import com.examapplication.interfaces.FilterInterface;
import com.examapplication.models.CategoryListModel;
import com.examapplication.ui.adapters.CategoryListAdapter;
import com.examapplication.ui.fragments.RunningNowFragment;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SendToLoginActivity extends ParentActivity implements View.OnClickListener, ApiServiceCaller, FilterInterface
{

    private Context mContext;
    public static Activity activityContext;
    private RelativeLayout relativeMain;
    private TextView txtContinue;
    private ImageView imgLogo;
    private Button btnFaculty, btnStudent;

    private RecyclerView recyclerCategoryList;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CategoryListAdapter categoryListAdapter;
    private ArrayList<CategoryListModel> categoryListModels = new ArrayList<>();

    private ArrayList<String> listOfSort = new ArrayList<>();
    private ArrayList<String> listOfStreams = new ArrayList<>();
    private ArrayList<String> listOfFaculty = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_login);

        mContext = this;
        activityContext = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            CommonUtility.askForPermissions(mContext, App.getInstance().permissions);
        }

        relativeMain = (RelativeLayout)findViewById(R.id.relative_main);
        imgLogo = (ImageView)findViewById(R.id.img_logo);
        txtContinue = (TextView)findViewById(R.id.txt_continue);
        txtContinue.setOnClickListener(this);

        btnFaculty = (Button)findViewById(R.id.btn_faculty);
        btnFaculty.setOnClickListener(this);
        btnStudent = (Button)findViewById(R.id.btn_student);
        btnStudent.setOnClickListener(this);

        getCategoryList();

        recyclerCategoryList = (RecyclerView)findViewById(R.id.recycler_category_list);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerCategoryList.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void onClick(View v)
    {
        if(v == btnFaculty)
        {
            callLoginScreen(getString(R.string.faculty));
        }

        if(v == btnStudent)
        {
            callLoginScreen(getString(R.string.student));
        }

        if(v == txtContinue)
        {
            RunningNowFragment.setValues(listOfSort, listOfStreams, listOfFaculty);
            Intent intent = new Intent(this, LandingStudentActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void callLoginScreen(String btnName)
    {
        AppPreferences.getInstance(mContext).putString(AppConstants.USER, btnName);
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.BUTTON_NAME, btnName);
        intent.putExtra(AppConstants.SEND_TO_LOGIN, bundle);
        startActivity(intent);
    }

    private void getCategoryList()
    {
        if (CommonUtility.getInstance(this).checkConnectivity(mContext))
        {
            try
            {
                showLoadingDialog();
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
                    if (jsonResponse.result != null && jsonResponse.result.equals(jsonResponse.result))
                    {
                        try
                        {
                            dismissLoadingDialog();
                            categoryListModels.addAll(jsonResponse.categories);
                            categoryListAdapter = new CategoryListAdapter(mContext, categoryListModels, this);
                            recyclerCategoryList.setAdapter(categoryListAdapter);
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

    @Override
    public void hashMapSort(HashMap sort) {

    }

    @Override
    public void hashMapStream(HashMap stream) {
        Collection<String> streamValues = stream.values();
        listOfStreams = new ArrayList<String>(streamValues);
        for (String value : listOfStreams)
        {
        }
    }

    @Override
    public void hashMapFaculty(HashMap faculty) {

    }

    public static void finishThis(Activity activityContext)
    {
        activityContext.finish();
    }
}
