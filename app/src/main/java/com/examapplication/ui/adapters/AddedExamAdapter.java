package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.models.AddedExamModel;
import com.examapplication.ui.fragments.ExamDetailsFragment;

import java.util.ArrayList;

/**
 * Created by Piyush on 02-08-2017.
 * Bynry
 */
public class AddedExamAdapter extends RecyclerView.Adapter<AddedExamAdapter.AddedExamHolder>
{

    public Context mContext;
    private ArrayList<AddedExamModel> runningNowList;

    public AddedExamAdapter(Context mContext, ArrayList<AddedExamModel> runningList)
    {
    }

    public AddedExamAdapter(Context context, ArrayList<AddedExamModel> techBitesCard, String message)
    {
        this.mContext = context;
        this.runningNowList = techBitesCard;
    }


    @Override
    public AddedExamAdapter.AddedExamHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_exam, null);
        AddedExamAdapter.AddedExamHolder viewHolder = new AddedExamAdapter.AddedExamHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddedExamAdapter.AddedExamHolder holder, int position)
    {
        holder.imgViewDetails.setOnClickListener(new View.OnClickListener()
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

        holder.imgEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

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


    public class AddedExamHolder extends RecyclerView.ViewHolder
    {
        public TextView txtExamName,txtTotalQuestions, txtStartDate;
        public ImageView imgViewDetails, imgEdit, imgDelete;

        public AddedExamHolder(View itemView)
        {
            super(itemView);
            txtExamName = (TextView)itemView.findViewById(R.id.input_exam_name);
            txtTotalQuestions = (TextView)itemView.findViewById(R.id.txt_total_question);
            txtStartDate = (TextView)itemView.findViewById(R.id.txt_start_date);

            imgViewDetails = (ImageView)itemView.findViewById(R.id.img_view_details);
            imgEdit = (ImageView)itemView.findViewById(R.id.img_edit);
            imgDelete = (ImageView)itemView.findViewById(R.id.img_delete);
        }
    }
}
