package com.examapplication.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.RunningNowModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AuthorDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AuthorDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorDetailsFragment extends Fragment implements View.OnClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context mContext;
    private RelativeLayout relativeMain;
    private ImageView imgCancel;
    private CircleImageView imgProfile;
    private TextView txtAuthor, txtAuthorName, txtCourseName, txtExperienceYear, txtDOBDate, txtAboutDesc;

    private ArrayList<RunningNowModel> runningNowList;
    private int position = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AuthorDetailsFragment()
    {
        // Required empty public constructor
    }

    public void getDetails(ArrayList<RunningNowModel> runningNowModels, int pos)
    {
        this.runningNowList = runningNowModels;
        this.position = pos;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuthorDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthorDetailsFragment newInstance(String param1, String param2)
    {
        AuthorDetailsFragment fragment = new AuthorDetailsFragment();
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
        if (getArguments() != null)
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
        View rootView = inflater.inflate(R.layout.fragment_author_details, container, false);

        mContext = getActivity();

        relativeMain = (RelativeLayout)rootView.findViewById(R.id.relative_main);
        relativeMain.setOnClickListener(null);

        imgCancel = (ImageView)rootView.findViewById(R.id.img_cancel);
        imgCancel.setOnClickListener(this);
        imgProfile = (CircleImageView)rootView.findViewById(R.id.img_profile);

        txtAuthor = (TextView)rootView.findViewById(R.id.txt_author);
        txtAuthorName = (TextView)rootView.findViewById(R.id.txt_author_name);
        txtCourseName = (TextView)rootView.findViewById(R.id.txt_course_name);
        txtExperienceYear = (TextView)rootView.findViewById(R.id.txt_exp_years);
        txtDOBDate = (TextView)rootView.findViewById(R.id.txt_dob_date);
        txtAboutDesc = (TextView)rootView.findViewById(R.id.txt_about_desc);

        txtAuthorName.setText(runningNowList.get(position).getExamAuthor());
        txtCourseName.setText(runningNowList.get(position).getExamEducation());
        txtExperienceYear.setText(runningNowList.get(position).getExamExperience());
        txtDOBDate.setText("");
        txtAboutDesc.setText(runningNowList.get(position).getExamAbout());

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
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

    @Override
    public void onClick(View v)
    {
        if(v == imgCancel)
        {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
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
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}