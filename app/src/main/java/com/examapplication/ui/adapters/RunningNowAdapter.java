package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examapplication.R;
import com.examapplication.models.RunningNowModel;

import java.util.ArrayList;

/**
 * Created by Piyush on 26-07-2017.
 * Bynry
 */
public class RunningNowAdapter extends RecyclerView.Adapter<RunningNowAdapter.RunningNowHolder>
{

    public Context mContext;
    private ArrayList<RunningNowModel> runningNowList;

    public RunningNowAdapter(Context mContext, ArrayList<RunningNowModel> runningList)
    {
    }

    public RunningNowAdapter(Context context, ArrayList<RunningNowModel> techBitesCard, String message)
    {
        this.mContext = context;
        this.runningNowList = techBitesCard;
    }


    @Override
    public RunningNowHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_running_now, null);
        RunningNowHolder viewHolder = new RunningNowHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RunningNowHolder holder, int position)
    {

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
        return 1;
    }


    public class RunningNowHolder extends RecyclerView.ViewHolder
    {
        public RunningNowHolder(View itemView)
        {
            super(itemView);
        }
    }
}