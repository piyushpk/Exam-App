package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examapplication.R;
import com.examapplication.models.QuestionModel;

import java.util.ArrayList;

/**
 * Created by Piyush on 15-08-2017.
 * Bynry
 */
public class SubmitAnswerAdapter extends RecyclerView.Adapter<SubmitAnswerAdapter.SubmitAnswerHolder>
{

    public Context mContext;
    private ArrayList<QuestionModel> questionModels;

    public SubmitAnswerAdapter(Context mContext, ArrayList<QuestionModel> runningList)
    {
    }

    public SubmitAnswerAdapter(Context context, ArrayList<QuestionModel> questionListModels, String message)
    {
        this.mContext = context;
        this.questionModels = questionListModels;
    }

    @Override
    public SubmitAnswerHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_submit_answer, null);
        SubmitAnswerAdapter.SubmitAnswerHolder viewHolder = new SubmitAnswerAdapter.SubmitAnswerHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubmitAnswerHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        /* if(questionModels != null && questionModels.size() > 0){
            return questionModels.size();
        }
        else{
            return 0;
        }*/
        return 20;
    }

    public class SubmitAnswerHolder extends RecyclerView.ViewHolder
    {
        public SubmitAnswerHolder(View itemView)
        {
            super(itemView);
        }
    }
}
