package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examapplication.R;
import com.examapplication.models.CategoryListModel;
import com.examapplication.models.CategoryListModel;

import java.util.ArrayList;

/**
 * Created by Piyush on 07-08-2017.
 * Bynry
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder>
{
    public Context mContext;
    private ArrayList<CategoryListModel> questionListModels;

    public CategoryListAdapter(Context mContext, ArrayList<CategoryListModel> categoryList)
    {
    }

    public CategoryListAdapter(Context context, ArrayList<CategoryListModel> categoryListModels, String message)
    {
        this.mContext = context;
        this.questionListModels = categoryListModels;
    }
    
    @Override
    public CategoryListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_category_list, null);
        CategoryListAdapter.CategoryListHolder viewHolder = new CategoryListAdapter.CategoryListHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CategoryListHolder extends RecyclerView.ViewHolder {
        public CategoryListHolder(View itemView) {
            super(itemView);
        }
    }
}
