package com.examapplication.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.examapplication.R;
import com.examapplication.models.CategoryListModel;
import com.examapplication.ui.adapters.CategoryListAdapter;

import java.util.ArrayList;

public class DashboardActivity extends ParentActivity
{
    private Context mContext;
    private RecyclerView recyclerCategoryList;
    private CategoryListAdapter categoryListAdapter;
    private ArrayList<CategoryListModel> categoryListModels;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mContext = this;

        recyclerCategoryList = (RecyclerView)findViewById(R.id.recycler_category_list);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerCategoryList.setLayoutManager(staggeredGridLayoutManager);
        categoryListModels = new ArrayList<>();
        categoryListAdapter = new CategoryListAdapter(mContext, categoryListModels, "");
        recyclerCategoryList.setAdapter(categoryListAdapter);
    }
}
