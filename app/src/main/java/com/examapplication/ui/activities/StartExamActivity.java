package com.examapplication.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.ui.fragments.MyComingSoonFragment;
import com.examapplication.ui.fragments.MyRunningExamFragment;
import com.examapplication.ui.fragments.QuestionPaperFragment;
import com.examapplication.ui.fragments.SubmitAnswerFragment;

import java.util.ArrayList;
import java.util.List;

public class StartExamActivity extends ParentActivity implements View.OnClickListener
{

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private TextView txtTitle;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        txtTitle = (TextView)findViewById(R.id.txt_name);
        txtTitle.setText(getString(R.string.start_exam));
        imgBack = (ImageView)findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new QuestionPaperFragment(), getString(R.string.question_paper));
        adapter.addFragment(new SubmitAnswerFragment(), getString(R.string.submit_answer));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v)
    {
        if(v == imgBack)
        {
            finish();
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitleList.get(position);
        }
    }
}
