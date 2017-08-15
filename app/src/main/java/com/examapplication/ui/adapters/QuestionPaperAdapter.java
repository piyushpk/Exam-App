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
public class QuestionPaperAdapter extends RecyclerView.Adapter<QuestionPaperAdapter.QuestionPaperHolder>
{

    public Context mContext;
    private ArrayList<QuestionModel> questionModels;

    public QuestionPaperAdapter(Context mContext, ArrayList<QuestionModel> runningList)
    {
    }

    public QuestionPaperAdapter(Context context, ArrayList<QuestionModel> questionListModels, String message)
    {
        this.mContext = context;
        this.questionModels = questionListModels;
    }

    @Override
    public QuestionPaperHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_question_list, null);
        QuestionPaperAdapter.QuestionPaperHolder viewHolder = new QuestionPaperAdapter.QuestionPaperHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionPaperHolder holder, int position)
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
        return 10;
    }

    public class QuestionPaperHolder extends RecyclerView.ViewHolder
    {
        public QuestionPaperHolder(View itemView)
        {
            super(itemView);
        }
    }
}
