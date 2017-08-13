package com.examapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.interfaces.ItemTouchHelperAdapter;
import com.examapplication.models.NotificationModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Piyush on 13-08-2017.
 * Bynry
 */
public class NotificationCardAdapter extends RecyclerView.Adapter<NotificationCardAdapter.NotificationCardHolder>
        implements ItemTouchHelperAdapter
{

    public Context mContext;
    private ArrayList<NotificationModel> mNotificationCard;

    public NotificationCardAdapter()
    {}

    public NotificationCardAdapter(Context context, ArrayList<NotificationModel> NotificationCards)
    {
        this.mContext = context;
        this.mNotificationCard = NotificationCards;

    }

    public NotificationCardAdapter(Context context)
    {
        this.mContext = context;
    }

    @Override
    public NotificationCardHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_notification_card, null);
        NotificationCardHolder viewHolder = new NotificationCardHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NotificationCardHolder holder, final int position)
    {
//        CommonUtils.setAnimation(holder.itemView, position, -1, mContext);
        final NotificationModel item = mNotificationCard.get(position);
        /*holder.date.setTypeface(bold);
        holder.date.setText(String.valueOf(item.date));
        holder.msg.setTypeface(regular);
        holder.msg.setText(String.valueOf(item.message));
        holder.title.setText(String.valueOf(item.title));*/

    }

    @Override
    public int getItemCount()
    {
        if(mNotificationCard != null && mNotificationCard.size() > 0)
            return mNotificationCard.size();
        else
            return 0;
    }

    public void setJobCard(ArrayList<NotificationModel> NotificationCards)
    {
        mNotificationCard = NotificationCards;
        notifyDataSetChanged();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(mNotificationCard, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position)
    {
        mNotificationCard.remove(position);
        notifyItemRemoved(position);
    }

    public  class NotificationCardHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout rl_notification_card;
        public TextView msg, date, title;
        public LinearLayout card;

        public NotificationCardHolder(View itemView)
        {
            super(itemView);
            rl_notification_card=(RelativeLayout)itemView.findViewById(R.id.rl_notification_card);
            msg = (TextView) itemView.findViewById(R.id.tv_notifications);
            date=(TextView)itemView.findViewById(R.id.tv_date);
            title = (TextView)itemView.findViewById(R.id.tv_notifications_title);
            card = (LinearLayout)itemView.findViewById(R.id.card);
        }
    }
}
