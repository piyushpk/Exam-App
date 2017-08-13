package com.examapplication.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.interfaces.ItemTouchHelperAdapter;
import com.examapplication.interfaces.OnStartDragListener;
import com.examapplication.models.NotificationModel;
import com.examapplication.services.SimpleItemTouchHelperCallback;
import com.examapplication.ui.adapters.NotificationCardAdapter;

import java.util.ArrayList;

public class NotificationActivity extends ParentActivity implements View.OnClickListener, OnStartDragListener
{
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Context mContext;
    private TextView txtTitle;
    private ImageView imgBack;
    private ItemTouchHelper mItemTouchHelper;
    private ArrayList<NotificationModel> notificationModels;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mContext = this;
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        txtTitle = (TextView)findViewById(R.id.txt_name);
        txtTitle.setText(getString(R.string.notifications));
        txtTitle.setOnClickListener(this);

        loadRecyclerView();
    }

    private void loadRecyclerView()
    {
        notificationModels = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        NotificationCardAdapter adapter = new NotificationCardAdapter(mContext, notificationModels);
        recyclerView.setAdapter(adapter);

        //To activate swipe animation Piyush : 25-02-17 starts
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        //To activate swipe animation Piyush : 25-02-17 ends
    }

    @Override
    public void onClick(View v)
    {
        if (v == imgBack)
        {
            finish();
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder)
    {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
