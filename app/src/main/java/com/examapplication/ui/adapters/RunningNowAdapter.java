package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.RunningNowModel;
import com.examapplication.ui.fragments.AuthorDetailsFragment;
import com.examapplication.ui.fragments.ExamDetailsFragment;

import java.util.ArrayList;

/**
 * Created by Piyush on 26-07-2017.
 * Bynry
 */
public class RunningNowAdapter extends RecyclerView.Adapter<RunningNowAdapter.RunningNowHolder>
{

    public Context mContext;
    private ArrayList<RunningNowModel> runningNowList;

    public RunningNowAdapter(Context mContext, ArrayList<RunningNowModel> runningList)
    {
    }

    public RunningNowAdapter(Context context, ArrayList<RunningNowModel> techBitesCard, String message)
    {
        this.mContext = context;
        this.runningNowList = techBitesCard;
    }


    @Override
    public RunningNowHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_running_now, null);
        RunningNowHolder viewHolder = new RunningNowHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RunningNowHolder holder, int position)
    {
        holder.txtAuthorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AuthorDetailsFragment fragment = new AuthorDetailsFragment();
                fragmentTransaction.add(android.R.id.content, fragment);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        holder.txtInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ExamDetailsFragment fragment = new ExamDetailsFragment();
                fragmentTransaction.add(android.R.id.content, fragment);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount()
    {
        /*if(mTechBitesCards != null && mTechBitesCards.size() > 0){
            return mTechBitesCards.size();
        }
        else{
            return 0;
        }*/
        return 10;
    }


    public class RunningNowHolder extends RecyclerView.ViewHolder
    {
        public TextView txtExamName, txtAuthorName, txtTotalMarks, txtMaxMarks, txtInfo, txtReleasingDate, txtStartDate,
                txtEndDate, txtSaveRs, txtRs1, txtRs2, txtYear;
        public Button btnCourseName, btnBuyNow;
        public CardView cardViewPrice;
        public RunningNowHolder(View itemView)
        {
            super(itemView);
            txtExamName = (TextView)itemView.findViewById(R.id.txt_exam_name);
            txtAuthorName = (TextView)itemView.findViewById(R.id.txt_author_name);
            txtTotalMarks = (TextView)itemView.findViewById(R.id.txt_max_marks);
            txtMaxMarks = (TextView)itemView.findViewById(R.id.txt_exam_name);
            txtInfo = (TextView)itemView.findViewById(R.id.txt_info);
            txtReleasingDate = (TextView)itemView.findViewById(R.id.txt_releasing_date);
            txtStartDate = (TextView)itemView.findViewById(R.id.txt_start_date);
            txtEndDate = (TextView)itemView.findViewById(R.id.txt_end_date);
            txtSaveRs = (TextView)itemView.findViewById(R.id.txt_save_rs);
            txtRs1 = (TextView)itemView.findViewById(R.id.txt_rs_1);
            txtRs2 = (TextView)itemView.findViewById(R.id.txt_rs_2);
            txtYear = (TextView)itemView.findViewById(R.id.txt_year);
        }
    }
}