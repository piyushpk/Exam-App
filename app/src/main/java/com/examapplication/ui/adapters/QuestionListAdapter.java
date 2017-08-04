package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.QuestionListModel;

import java.util.ArrayList;

/**
 * Created by Piyush on 04-08-2017.
 * Bynry
 */
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionListHolder>
{

    public Context mContext;
    private ArrayList<QuestionListModel> questionListModels;

    public QuestionListAdapter(Context mContext, ArrayList<QuestionListModel> runningList)
    {
    }

    public QuestionListAdapter(Context context, ArrayList<QuestionListModel> questionListModels, String message)
    {
        this.mContext = context;
        this.questionListModels = questionListModels;
    }


    @Override
    public QuestionListAdapter.QuestionListHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_question_list, null);
        QuestionListAdapter.QuestionListHolder viewHolder = new QuestionListAdapter.QuestionListHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionListAdapter.QuestionListHolder holder, int position)
    {
        holder.txtQuestion.setText(questionListModels.get(position).getQuestion());
        holder.txtSubQue1.setText(questionListModels.get(position).getSubQuestion1());
        holder.txtSubQue2.setText(questionListModels.get(position).getSubQuestion2());
        holder.txtSubQue3.setText(questionListModels.get(position).getSubQuestion3());
        holder.txtSubQue4.setText(questionListModels.get(position).getSubQuestion4());
        holder.txtSubQue5.setText(questionListModels.get(position).getSubQuestion5());
        holder.txtSubQue6.setText(questionListModels.get(position).getSubQuestion6());
    }

    @Override
    public int getItemCount()
    {
        if(questionListModels != null && questionListModels.size() > 0){
            return questionListModels.size();
        }
        else{
            return 0;
        }
//        return 10;
    }


    public class QuestionListHolder extends RecyclerView.ViewHolder
    {
        public TextView txtQuestion, txtSubQue1, txtSubQue2, txtSubQue3, txtSubQue4, txtSubQue5, txtSubQue6;

        public QuestionListHolder(View itemView)
        {
            super(itemView);
            txtQuestion = (TextView)itemView.findViewById(R.id.txt_question);
            txtSubQue1 = (TextView)itemView.findViewById(R.id.txt_sub_que_1);
            txtSubQue2 = (TextView)itemView.findViewById(R.id.txt_sub_que_2);
            txtSubQue3 = (TextView)itemView.findViewById(R.id.txt_sub_que_3);
            txtSubQue4 = (TextView)itemView.findViewById(R.id.txt_sub_que_4);
            txtSubQue5 = (TextView)itemView.findViewById(R.id.txt_sub_que_5);
            txtSubQue6 = (TextView)itemView.findViewById(R.id.txt_sub_que_6);
        }
    }
}