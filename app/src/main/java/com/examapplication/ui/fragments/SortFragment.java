package com.examapplication.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.examapplication.R;
import com.examapplication.ui.activities.FilterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortFragment extends Fragment implements View.OnClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Context mContext;
    private RadioButton chkAscending, chkDescending;
    private List<String> sort = new ArrayList<>();
    private List<String> stream = new ArrayList<>();
    private List<String> faculty = new ArrayList<>();


    public SortFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SortFragment newInstance(String param1, String param2) {
        SortFragment fragment = new SortFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sort, container, false);
        mContext = getActivity().getApplicationContext();

        chkAscending = (RadioButton) rootView.findViewById(R.id.radio_ascending);
        chkAscending.setOnClickListener(this);
        chkDescending = (RadioButton) rootView.findViewById(R.id.radio_descending);
        chkDescending.setOnClickListener(this);

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
    public void onClick(View v)
    {
        if(v == chkAscending)
        {
            if(chkAscending.isChecked())
            {
                try{sort.remove(0);}
                catch(Exception e){}
                sort.add(0, "Sale");
                FilterActivity.getSort(sort);
            }
        }

        if(v == chkDescending)
        {
            if(chkDescending.isChecked())
            {
                try{sort.remove(0);}
                catch(Exception e){}
                sort.add(0, "-Sale");
                FilterActivity.getSort(sort);
            }
        }
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

}
