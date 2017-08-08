package com.examapplication.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.SubmittedExamModel;
import com.examapplication.ui.activities.StudentListActivity;

import java.util.ArrayList;

/**
 * Created by Piyush on 08-08-2017.
 * Bynry
 */
public class SubmittedExamAdapter extends RecyclerView.Adapter<SubmittedExamAdapter.SubmittedExamHolder>
{

    public Context mContext;
    private ArrayList<SubmittedExamModel> submittedExamModels;

    public SubmittedExamAdapter(Context mContext, ArrayList<SubmittedExamModel> submittedExamModels)
    {
    }

    public SubmittedExamAdapter(Context context, ArrayList<SubmittedExamModel> submittedExamModels, String message)
    {
        this.mContext = context;
        this.submittedExamModels = submittedExamModels;
    }

    @Override
    public SubmittedExamHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_submitted_exam, null);
        SubmittedExamAdapter.SubmittedExamHolder viewHolder = new SubmittedExamAdapter.SubmittedExamHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubmittedExamHolder holder, int position)
    {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StudentListActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return 10;
    }

    public class SubmittedExamHolder extends RecyclerView.ViewHolder
    {
        public CardView cardView;
        public TextView txtExamName, txtResultDate, txtTotalStudents, txtSubmittedStudent;

        public SubmittedExamHolder(View itemView)
        {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card_view);
            txtExamName = (TextView)itemView.findViewById(R.id.txt_exam_name);
            txtResultDate = (TextView)itemView.findViewById(R.id.txt_result_date);
            txtTotalStudents = (TextView)itemView.findViewById(R.id.txt_total_student);
            txtSubmittedStudent = (TextView)itemView.findViewById(R.id.txt_submitted_student);
        }
    }
}
