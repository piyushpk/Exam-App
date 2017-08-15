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
import com.examapplication.models.QuestionModel;
import com.examapplication.ui.adapters.QuestionPaperAdapter;
import com.examapplication.ui.adapters.SubmitAnswerAdapter;
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
 * {@link SubmitAnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubmitAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitAnswerFragment extends ParentFragment implements ApiServiceCaller
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerQuestionPaper;
    private Context mContext;
    private LinearLayoutManager layoutManager;

    public SubmitAnswerFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubmitAnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubmitAnswerFragment newInstance(String param1, String param2)
    {
        SubmitAnswerFragment fragment = new SubmitAnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_submit_answer, container, false);
        mContext = getContext();

        recyclerQuestionPaper = (RecyclerView)rootView.findViewById(R.id.recycler_submitted_answers);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerQuestionPaper.setLayoutManager(layoutManager);
        ArrayList<QuestionModel> questionModels = new ArrayList<>();
        SubmitAnswerAdapter runningNowAdapter = new SubmitAnswerAdapter(mContext,
                questionModels, "");
        recyclerQuestionPaper.setAdapter(runningNowAdapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if(mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
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
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume()
    {
        super.onResume();
//        getQuestionPaper();
    }

    private void getQuestionPaper()
    {
        if (CommonUtility.getInstance(mContext).checkConnectivity(mContext))
        {
            try
            {
                showLoadingDialog(mContext);
                JSONObject jsonObject = new JSONObject();
                String token = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");

                JsonObjectRequest request = WebRequest.callPostMethod(mContext, jsonObject, Request.Method.GET,
                        ApiConstants.GET_QUESTION_PAPER_URL, ApiConstants.GET_QUESTION_PAPER, this, token);
                App.getInstance().addToRequestQueue(request, ApiConstants.GET_QUESTION_PAPER);

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
            case ApiConstants.GET_QUESTION_PAPER:
            {
                if (jsonResponse != null)
                {
                    if (jsonResponse.SUCCESS != null && jsonResponse.result.equals(jsonResponse.SUCCESS))
                    {
                        try
                        {
                            dismissLoadingDialog();
                            ArrayList<QuestionModel> questionModels = new ArrayList<>();
//                            questionModels.addAll(jsonResponse.myexamorder.getMyRunningExam());
                            QuestionPaperAdapter runningNowAdapter = new QuestionPaperAdapter(mContext,
                                    questionModels, "");
                            recyclerQuestionPaper.setAdapter(runningNowAdapter);
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
            case ApiConstants.GET_QUESTION_PAPER:
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
            case ApiConstants.GET_QUESTION_PAPER:
            {
                Toast.makeText(mContext, AppConstants.API_FAIL_MESSAGE, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
