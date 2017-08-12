package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.examapplication.R;
import com.examapplication.interfaces.FilterInterface;
import com.examapplication.models.CategoryListModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Piyush on 10-08-2017.
 * Bynry
 */
public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamHolder>
{

    public Context mContext;
    private ArrayList<CategoryListModel> categoryListModels;
    private HashMap<Integer, String> hashMapStream = new HashMap<>();
    private FilterInterface filterInterface;

    public StreamAdapter(Context mContext, ArrayList<CategoryListModel> submittedExamModels)
    {
    }

    public StreamAdapter(Context context, ArrayList<CategoryListModel> submittedExamModels, FilterInterface fContext)
    {
        this.mContext = context;
        this.categoryListModels = submittedExamModels;
        this.filterInterface = (FilterInterface) fContext;
    }

    @Override
    public StreamHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_filter_layout, null);
        StreamAdapter.StreamHolder viewHolder = new StreamAdapter.StreamHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final StreamHolder holder, final int position)
    {
        holder.chkStream.setText(categoryListModels.get(position).getCategoryName());
        holder.chkStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.chkStream.isChecked())
                {
                    hashMapStream.put(position, categoryListModels.get(position).getCategoryId());
                }
                else
                {
                    hashMapStream.remove(position);
                }
                filterInterface.hashMapStream(hashMapStream);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        if(categoryListModels != null && categoryListModels.size() > 0){
            return categoryListModels.size();
        }
        else{
            return 0;
        }
//        return 10;
    }

    public class StreamHolder extends RecyclerView.ViewHolder
    {
        public CheckBox chkStream;

        public StreamHolder(View itemView)
        {
            super(itemView);
            chkStream = (CheckBox)itemView.findViewById(R.id.checkbox);
        }
    }
}
