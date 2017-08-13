package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.RunningNowModel;

import java.util.ArrayList;

/**
 * Created by Piyush on 08-08-2017.
 * Bynry
 */
public class MySubmittedExamAdapter extends RecyclerView.Adapter<MySubmittedExamAdapter.SubmittedExamHolder>
{

    private Context mContext;
    private ArrayList<RunningNowModel> submittedExamModels;
    private String comingFrom = "";

    public MySubmittedExamAdapter(Context mContext, ArrayList<RunningNowModel> submittedExamModels)
    {
    }

    public MySubmittedExamAdapter(Context context, ArrayList<RunningNowModel> submittedExamModels, String message)
    {
        this.mContext = context;
        this.submittedExamModels = submittedExamModels;
        this.comingFrom = message;
    }

    @Override
    public SubmittedExamHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_submitted_exam, null);
        MySubmittedExamAdapter.SubmittedExamHolder viewHolder = new MySubmittedExamAdapter.SubmittedExamHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubmittedExamHolder holder, int position)
    {
        holder.txtFirst.setText(submittedExamModels.get(position).getExamName());
        holder.txtSecond.setText(submittedExamModels.get(position).getExamEndDate());
        holder.txtThird.setText(submittedExamModels.get(position).getExamSubmittedDate());
        holder.txtFourth.setText(submittedExamModels.get(position).getExamResultDate());
    }

    @Override
    public int getItemCount()
    {
        if(submittedExamModels != null && submittedExamModels.size() > 0){
            return submittedExamModels.size();
        }
        else{
            return 0;
        }
//        return 10;
    }

    public class SubmittedExamHolder extends RecyclerView.ViewHolder
    {
        public CardView cardView;
        public TextView txtFirst, txtSecond, txtThird, txtFourth;
        public TextView lblFirst, lblSecond, lblThird, lblFourth;

        public SubmittedExamHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            txtFirst = (TextView) itemView.findViewById(R.id.txt_first);
            txtSecond = (TextView) itemView.findViewById(R.id.txt_second);
            txtThird = (TextView) itemView.findViewById(R.id.txt_third);
            txtFourth = (TextView) itemView.findViewById(R.id.txt_fourth);

            lblFirst = (TextView) itemView.findViewById(R.id.lbl_first);
            lblSecond = (TextView) itemView.findViewById(R.id.lbl_second);
            lblThird = (TextView) itemView.findViewById(R.id.lbl_third);
            lblFourth = (TextView) itemView.findViewById(R.id.lbl_fourth);

            if(comingFrom.equals("Student"))
            {
                lblFirst.setText(R.string.exam_name);
                lblSecond.setText(R.string.end_date_card);
                lblThird.setText(R.string.submitted_date);
                lblFourth.setText(R.string.result_date);
            }
            else
            {
                lblFirst.setText(R.string.exam_name);
                lblSecond.setText(R.string.result_date);
                lblThird.setText(R.string.total_student);
                lblFourth.setText(R.string.submitted_student);
            }
        }
    }
}
