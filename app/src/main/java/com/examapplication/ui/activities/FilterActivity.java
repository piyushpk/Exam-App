package com.examapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.examapplication.R;
import com.examapplication.ui.adapters.StreamAdapter;
import com.examapplication.ui.fragments.ComingSoonFragment;
import com.examapplication.ui.fragments.FacultyFragment;
import com.examapplication.ui.fragments.RunningNowFragment;
import com.examapplication.ui.fragments.SortFragment;
import com.examapplication.ui.fragments.StreamFragment;
import com.examapplication.utility.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends ParentActivity implements View.OnClickListener
{

    private Context mContext;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RelativeLayout relativeMain;
    private ImageView imgCancel;
    private Button btnApply, btnClear;

    private static List<String> mSort = new ArrayList<>();
    private static List<String> mStream = new ArrayList<>();
    private static List<String> mFaculty = new ArrayList<>();

    private String comingFrom = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        mContext = this;
        relativeMain = (RelativeLayout)findViewById(R.id.relative_main);
        relativeMain.setOnClickListener(null);
        imgCancel = (ImageView)findViewById(R.id.img_cancel);
        imgCancel.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        btnApply = (Button)findViewById(R.id.btn_apply);
        btnApply.setOnClickListener(this);
        btnClear = (Button)findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(AppConstants.LANDING_STUDENT);
        if(bundle != null)
        {
            comingFrom = bundle.getString(AppConstants.VISIBLE_FRAG);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SortFragment(), getString(R.string.sort));
        adapter.addFragment(new StreamFragment(), getString(R.string.stream));
        adapter.addFragment(new FacultyFragment(), getString(R.string.faculty));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v == imgCancel)
        {
            finish();
        }

        if(v == btnApply)
        {
            if(comingFrom.equals(getString(R.string.running_now)))
                RunningNowFragment.setValues(mSort, mStream, mFaculty);
            else
                ComingSoonFragment.setValues(mSort, mStream, mFaculty);
            finish();
        }

        if(v == btnClear)
        {
            mSort.clear();mStream.clear();mFaculty.clear();

            if(comingFrom.equals(getString(R.string.running_now)))
                RunningNowFragment.setValues(mSort, mStream, mFaculty);
            else
                ComingSoonFragment.setValues(mSort, mStream, mFaculty);

            finish();
        }
    }

    public static void getSort(List<String> sort)
    {
        mSort = sort;
    }
    public static void getStream(List<String> stream){
        mStream = stream;
    }
    public static void getFaculty(List<String> faculty)
    {
        mFaculty = faculty;
    }
}
