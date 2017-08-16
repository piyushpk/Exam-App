package com.examapplication.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examapplication.R;
import com.examapplication.interfaces.ApiServiceCaller;
import com.examapplication.models.RunningNowModel;
import com.examapplication.ui.adapters.MyRunningExamAdapter;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyComingSoonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyComingSoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyComingSoonFragment extends ParentFragment implements ApiServiceCaller
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerComingSoon;
    private Context mContext;
    private LinearLayoutManager layoutManager;

    private static int pageNo = 1;

    public MyComingSoonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyComingSoonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyComingSoonFragment newInstance(String param1, String param2) {
        MyComingSoonFragment fragment = new MyComingSoonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_coming_soon, container, false);
        mContext = getContext();

        recyclerComingSoon = (RecyclerView)rootView.findViewById(R.id.recycler_my_coming_exam);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerComingSoon.setLayoutManager(layoutManager);
        ArrayList<RunningNowModel> runningNowModels = new ArrayList<>();
        MyRunningExamAdapter runningNowAdapter = new MyRunningExamAdapter(mContext, runningNowModels,
                getString(R.string.coming));
        recyclerComingSoon.setAdapter(runningNowAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            getExamList(pageNo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if(mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getExamList(int page)
    {
        if (CommonUtility.getInstance(mContext).checkConnectivity(mContext))
        {
            try
            {
                showLoadingDialog(mContext);
                JSONObject jsonObject = new JSONObject();
                String token = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_MY_COMING_EXAM_URL+page+"/", ApiConstants.GET_MY_COMING_EXAM, this, token);
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_MY_COMING_EXAM);

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
            case ApiConstants.GET_MY_COMING_EXAM:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.result != null && jsonResponse.result.equals(jsonResponse.result))
                    {
                        try
                        {
                            dismissLoadingDialog();
                            ArrayList<RunningNowModel> runningNowModels = new ArrayList<>();
                            runningNowModels.addAll(jsonResponse.myexamcomingorder.getMyComingExam());
                            MyRunningExamAdapter runningNowAdapter = new MyRunningExamAdapter(mContext, runningNowModels,
                                    getString(R.string.coming));
                            recyclerComingSoon.setAdapter(runningNowAdapter);
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
            case ApiConstants.GET_MY_COMING_EXAM:
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
            case ApiConstants.GET_MY_COMING_EXAM:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
