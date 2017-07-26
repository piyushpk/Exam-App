package com.examapplication.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.examapplication.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 7/17/2017.
 */

public class DayDecorator implements DayViewDecorator {


    CalendarDay date;
    private Drawable highlightDrawable;
    private Context context;


    public DayDecorator(Context context) {
        this.context = context;
        highlightDrawable = this.context.getResources().getDrawable(R.drawable.selected_date_bg);
    }


    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.5f));

    }


    public ArrayList<List<CalendarDay>> shouldDecorate(ArrayList<List<CalendarDay>> calendarDayList) {
        return calendarDayList;
    }


}

