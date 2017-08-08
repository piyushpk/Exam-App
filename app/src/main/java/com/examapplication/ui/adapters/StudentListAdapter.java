package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examapplication.R;
import com.examapplication.models.StudentListModel;

import java.util.ArrayList;

/**
 * Created by Piyush on 08-08-2017.
 * Bynry
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListHolder>
{

    public Context mContext;
    private ArrayList<StudentListModel> studentListModels;

    public StudentListAdapter(Context mContext, ArrayList<StudentListModel> studentListModels)
    {
    }

    public StudentListAdapter(Context context, ArrayList<StudentListModel> studentListModels, String message)
    {
        this.mContext = context;
        this.studentListModels = studentListModels;
    }

    @Override
    public StudentListHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_student_list, null);
        StudentListAdapter.StudentListHolder viewHolder = new StudentListAdapter.StudentListHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentListHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 10;
    }

    public class StudentListHolder extends RecyclerView.ViewHolder
    {
        public StudentListHolder(View itemView)
        {
            super(itemView);
        }
    }
}
