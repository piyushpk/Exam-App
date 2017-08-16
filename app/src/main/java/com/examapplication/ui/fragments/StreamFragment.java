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
import com.examapplication.interfaces.FilterInterface;
import com.examapplication.models.CategoryListModel;
import com.examapplication.ui.activities.FilterActivity;
import com.examapplication.ui.adapters.StreamAdapter;
import com.examapplication.utility.App;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.CommonUtility;
import com.examapplication.webservices.ApiConstants;
import com.examapplication.webservices.JsonResponse;
import com.examapplication.webservices.WebRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StreamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StreamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StreamFragment extends Fragment implements ApiServiceCaller, FilterInterface
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerStream;
    private Context mContext;
    private LinearLayoutManager layoutManager;
    private StreamAdapter streamAdapter;
    private ArrayList<CategoryListModel> categoryListModels;

    public StreamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StreamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StreamFragment newInstance(String param1, String param2) {
        StreamFragment fragment = new StreamFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        mContext = getActivity().getApplicationContext();

        recyclerStream = (RecyclerView)rootView.findViewById(R.id.recycler_stream);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerStream.setLayoutManager(layoutManager);
        categoryListModels = new ArrayList<>();

        getCategoryList();

        return rootView;
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

    @Override
    public void hashMapSort(HashMap sort) {

    }

    @Override
    public void hashMapStream(HashMap stream) {
        Collection<String> streamValues = stream.values();
        ArrayList<String> listOfStreams = new ArrayList<String>(streamValues);
        for (String value : listOfStreams)
        {
        }
        FilterActivity.getStream(listOfStreams);
    }

    @Override
    public void hashMapFaculty(HashMap faculty) {

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

    private void getCategoryList()
    {
        if (CommonUtility.getInstance(mContext).checkConnectivity(mContext))
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
                    if (jsonResponse.result != null && jsonResponse.result.equals(jsonResponse.result))
                    {
                        try
                        {
                            categoryListModels.addAll(jsonResponse.categories);
                            streamAdapter = new StreamAdapter(mContext, categoryListModels, StreamFragment.this);
                            recyclerStream.setAdapter(streamAdapter);
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
        switch (label)
        {
            case ApiConstants.GET_CATEGORY:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
