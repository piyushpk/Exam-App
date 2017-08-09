package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public RunningNowAdapter(Context mContext, ArrayList<RunningNowModel> runningNowModels)
    {
    }

    public RunningNowAdapter(Context context, ArrayList<RunningNowModel> runningNowModels, String message)
    {
        this.mContext = context;
        this.runningNowList = runningNowModels;
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
    public void onBindViewHolder(RunningNowHolder holder, final int position)
    {
        holder.txtExamName.setText(runningNowList.get(position).getExamName());
        holder.txtAuthorName.setText(runningNowList.get(position).getExamAuthor());
        holder.btnCourseName.setText(runningNowList.get(position).getExamEducation());
        holder.txtTotalMarks.setText(mContext.getString(R.string.total_marks)+" "+
                runningNowList.get(position).getExamTotalMarks());
        holder.txtMaxMarks.setText(mContext.getString(R.string.max_mark_s)+" "+
                runningNowList.get(position).getExamPassingMarks());
        holder.txtStartDate.setText(mContext.getString(R.string.start_date)+" "+
                runningNowList.get(position).getExamStartDate());
        holder.txtEndDate.setText(mContext.getString(R.string.end_date)+" "+
                runningNowList.get(position).getExamEndDate());
        holder.txtSaveRs.setText(mContext.getString(R.string.save_rs)+" "+
                runningNowList.get(position).getExamOfferPrice());
        holder.txtRs1.setText(runningNowList.get(position).getExamSalePrice());
        float pricePaid = Float.parseFloat(runningNowList.get(position).getExamSalePrice())
                - Float.parseFloat(runningNowList.get(position).getExamOfferPrice());
        holder.txtRs2.setText(mContext.getString(R.string.rs)+""+pricePaid);

        holder.txtAuthorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AuthorDetailsFragment fragment = new AuthorDetailsFragment();
                fragment.getDetails(runningNowList, position);
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
                fragment.getDetails(runningNowList, position);
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
        if(runningNowList != null && runningNowList.size() > 0){
            return runningNowList.size();
        }
        else{
            return 0;
        }
//        return 10;
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
            txtTotalMarks = (TextView)itemView.findViewById(R.id.txt_total_marks);
            txtMaxMarks = (TextView)itemView.findViewById(R.id.txt_max_marks);
            txtInfo = (TextView)itemView.findViewById(R.id.txt_info);
            txtReleasingDate = (TextView)itemView.findViewById(R.id.txt_releasing_date);
            txtStartDate = (TextView)itemView.findViewById(R.id.txt_start_date);
            txtEndDate = (TextView)itemView.findViewById(R.id.txt_end_date);
            txtSaveRs = (TextView)itemView.findViewById(R.id.txt_save_rs);
            txtRs1 = (TextView)itemView.findViewById(R.id.txt_rs_1);
            txtRs2 = (TextView)itemView.findViewById(R.id.txt_rs_2);
            txtYear = (TextView)itemView.findViewById(R.id.txt_year);

            btnCourseName = (Button) itemView.findViewById(R.id.btn_course_name);
            btnBuyNow = (Button)itemView.findViewById(R.id.btn_buy_now);
        }
    }
}