package com.examapplication.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examapplication.R;
import com.examapplication.interfaces.ApiServiceCaller;
import com.examapplication.models.RunningNowModel;
import com.examapplication.models.SubmittedExamModel;
import com.examapplication.ui.adapters.MyRunningExamAdapter;
import com.examapplication.ui.adapters.SubmittedExamAdapter;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONObject;

import java.util.ArrayList;

public class MySubmittedExam extends ParentActivity implements ApiServiceCaller, View.OnClickListener
{

    private RecyclerView recyclerComingSoon;
    private Context mContext;
    private LinearLayoutManager layoutManager;

    private static int pageNo = 1;
    private TextView txtTitle;
    private ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_submitted_exam);
        mContext = this;
        recyclerComingSoon = (RecyclerView)findViewById(R.id.recycler_my_submitted_exam);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerComingSoon.setLayoutManager(layoutManager);
        ArrayList<SubmittedExamModel> submittedExamModels = new ArrayList<>();
        SubmittedExamAdapter submittedExamAdapter = new SubmittedExamAdapter(mContext,
                submittedExamModels, "");
        recyclerComingSoon.setAdapter(submittedExamAdapter);

        txtTitle = (TextView)findViewById(R.id.txt_name);
        txtTitle.setText(getString(R.string.my_submitted_exam));
        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            getExamList(pageNo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getExamList(int page)
    {
        if (CommonUtility.getInstance(mContext).checkConnectivity(mContext))
        {
            try
            {
                showLoadingDialog();
                JSONObject jsonObject = new JSONObject();
                /*JSONArray arraySortBy = new JSONArray(sort);
                JSONArray arrayCategories = new JSONArray(stream);
                JSONArray arrayFaculties = new JSONArray(faculty);
                jsonObject.put("categories", arrayCategories);
                jsonObject.put("faculties", arrayFaculties);
                jsonObject.put("sortedby", arraySortBy);*/

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_RUNNING_EXAM_LIST_URL+page+"/", ApiConstants.GET_RUNNING_EXAM_LIST, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_RUNNING_EXAM_LIST);

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
            case ApiConstants.GET_RUNNING_EXAM_LIST:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            dismissLoadingDialog();
                            ArrayList<SubmittedExamModel> submittedExamModels = new ArrayList<>();
//                            runningNowModels.addAll(jsonResponse.examdata.getRunningExamList());
                            SubmittedExamAdapter submittedExamAdapter = new SubmittedExamAdapter(mContext,
                                    submittedExamModels, "");
                            recyclerComingSoon.setAdapter(submittedExamAdapter);
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
            case ApiConstants.GET_RUNNING_EXAM_LIST:
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
            case ApiConstants.GET_RUNNING_EXAM_LIST:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v == imgBack)
        {
            finish();
        }
    }
}
