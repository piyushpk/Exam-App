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
import com.examapplication.ui.adapters.RunningNowAdapter;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComingSoonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComingSoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComingSoonFragment extends Fragment implements ApiServiceCaller
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerRunningNow;
    private Context mContext;
    private LinearLayoutManager layoutManager;
    private RunningNowAdapter runningNowAdapter;
    private ArrayList<RunningNowModel> runningNowModels;

    public static List<String> sort = new ArrayList<>();
    public static List<String> stream = new ArrayList<>();
    public static List<String> faculty = new ArrayList<>();

    private int pageNo = 1;

    public ComingSoonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComingSoonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComingSoonFragment newInstance(String param1, String param2) {
        ComingSoonFragment fragment = new ComingSoonFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        mContext = getActivity().getApplicationContext();

        recyclerRunningNow = (RecyclerView)rootView.findViewById(R.id.recycler_coming_soon);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerRunningNow.setLayoutManager(layoutManager);

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

    private void getExamList(int page)
    {
        if (CommonUtility.getInstance(mContext).checkConnectivity(mContext))
        {
            try
            {
                JSONObject jsonObject = new JSONObject();
                JSONArray arraySortBy = new JSONArray(sort);
                JSONArray arrayCategories = new JSONArray(stream);
                JSONArray arrayFaculties = new JSONArray(faculty);
                jsonObject.put("categories", arrayCategories);
                jsonObject.put("faculties", arrayFaculties);
                jsonObject.put("sortedby", arraySortBy);

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.POST,
                        ApiConstants.GET_COMING_EXAM_LIST_URL+page+"/", ApiConstants.GET_COMING_EXAM_LIST, this, "");
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_COMING_EXAM_LIST);

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
            case ApiConstants.GET_COMING_EXAM_LIST:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.result != null && jsonResponse.result.equals(jsonResponse.result))
                    {
                        try
                        {
                            runningNowModels = new ArrayList<>();
                            runningNowModels.addAll(jsonResponse.coming_soon.getComingExamList());
                            runningNowAdapter = new RunningNowAdapter(mContext, runningNowModels, "");
                            recyclerRunningNow.setAdapter(runningNowAdapter);
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
            case ApiConstants.GET_COMING_EXAM_LIST:
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
            case ApiConstants.GET_COMING_EXAM_LIST:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    public static void setValues(List<String> mSort, List<String> mStream, List<String> mFaculty)
    {
        sort = mSort;
        stream = mStream;
        faculty = mFaculty;
    }
}
