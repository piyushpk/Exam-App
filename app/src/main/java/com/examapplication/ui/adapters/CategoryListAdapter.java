package com.examapplication.ui.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.examapplication.R;
import com.examapplication.interfaces.FilterInterface;
import com.examapplication.models.CategoryListModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Piyush on 07-08-2017.
 * Bynry
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder>
{
    public Context mContext;
    private ArrayList<CategoryListModel> categoryListModels;
    private FilterInterface filterInterface;
    private HashMap<Integer, String> hashMapStream = new HashMap<>();

    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private long lastPressTime;
    private boolean mHasDoubleClicked = false;

    public CategoryListAdapter(Context mContext, ArrayList<CategoryListModel> categoryList)
    {}

    public CategoryListAdapter(Context context, ArrayList<CategoryListModel> categoryListModels, FilterInterface fContext)
    {
        this.mContext = context;
        this.categoryListModels = categoryListModels;
        this.filterInterface = (FilterInterface) fContext;
    }
    
    @Override
    public CategoryListHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_category_list, null);
        CategoryListAdapter.CategoryListHolder viewHolder = new CategoryListAdapter.CategoryListHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CategoryListHolder holder, final int position)
    {
        holder.btnCategoryItems.setText(categoryListModels.get(position).getCategoryName());

        holder.btnCategoryItems.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get current time in nano seconds.
                long pressTime = System.currentTimeMillis();
                // If double click...
                if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL)
                {
                    holder.btnCategoryItems.setBackgroundResource(R.drawable.dashboard_button_unselected);
                    mHasDoubleClicked = true;
                    hashMapStream.remove(position);
                    filterInterface.hashMapStream(hashMapStream);
                }
                else
                {
                    // If not double click....
                    mHasDoubleClicked = false;
                    Handler myHandler = new Handler()
                    {
                        public void handleMessage(Message m)
                        {
                            if (!mHasDoubleClicked)
                            {
                                holder.btnCategoryItems.setBackgroundResource(R.drawable.dashboard_button_selected);
                                hashMapStream.put(position, categoryListModels.get(position).getCategoryId());
                                filterInterface.hashMapStream(hashMapStream);
                            }
                        }
                    };
                    Message m = new Message();
                    myHandler.sendMessageDelayed(m,DOUBLE_PRESS_INTERVAL);
                }
                // record the last time the menu button was pressed.
                lastPressTime = pressTime;
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
    }

    public class CategoryListHolder extends RecyclerView.ViewHolder
    {
        public Button btnCategoryItems;

        public CategoryListHolder(View itemView)
        {
            super(itemView);
            btnCategoryItems = (Button)itemView.findViewById(R.id.btn_category_items);
        }
    }
}
