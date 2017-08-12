package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.examapplication.R;
import com.examapplication.interfaces.FilterInterface;
import com.examapplication.models.FacultyModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Piyush on 11-08-2017.
 * Bynry
 */
public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyHolder>
{

    public Context mContext;
    private ArrayList<FacultyModel> facultyModels;
    private HashMap<Integer, String> hashMapFaculty = new HashMap<>();
    private FilterInterface filterInterface;

    public FacultyAdapter(Context mContext, ArrayList<FacultyModel> submittedExamModels)
    {
    }

    public FacultyAdapter(Context context, ArrayList<FacultyModel> submittedExamModels, FilterInterface fContext)
    {
        this.mContext = context;
        this.facultyModels = submittedExamModels;
        this.filterInterface = (FilterInterface) fContext;
    }

    @Override
    public FacultyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_filter_layout, null);
        FacultyAdapter.FacultyHolder viewHolder = new FacultyAdapter.FacultyHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FacultyHolder holder, final int position)
    {

        holder.chkStream.setText(facultyModels.get(position).getFacultyName());
        holder.chkStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.chkStream.isChecked())
                {
                    hashMapFaculty.put(position, facultyModels.get(position).getFacultyId());
                }
                else
                {
                    hashMapFaculty.remove(position);
                }
                filterInterface.hashMapFaculty(hashMapFaculty);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(facultyModels != null && facultyModels.size() > 0){
            return facultyModels.size();
        }
        else{
            return 0;
        }
//        return 10;
    }

    public class FacultyHolder extends RecyclerView.ViewHolder
    {
        public CheckBox chkStream;

        public FacultyHolder(View itemView)
        {
            super(itemView);
            chkStream = (CheckBox)itemView.findViewById(R.id.checkbox);
        }
    }
}
