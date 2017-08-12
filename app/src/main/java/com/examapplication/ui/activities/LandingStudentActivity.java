package com.examapplication.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examapplication.R;
import com.examapplication.ui.fragments.ComingSoonFragment;
import com.examapplication.ui.fragments.RunningNowFragment;
import com.examapplication.utility.AppConstants;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LandingStudentActivity extends ParentActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RelativeLayout mainView;
    private String visibleFragment = "";
    private Intent intent;

    private TextView txtFullName, txtEmail;
    private CircleImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_student);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mainView = (RelativeLayout) findViewById(R.id.mainView);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();


            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        txtFullName = (TextView)header.findViewById(R.id.txt_full_name);
        txtFullName.setText("Piyush Kalmegh");
        txtEmail = (TextView)header.findViewById(R.id.txt_email);
        txtEmail.setText("p.kalmegh1@gmail.com");
        imgProfile = (CircleImageView)header.findViewById(R.id.img_profile);

        visibleFragment = getString(R.string.running_now);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RunningNowFragment(), getString(R.string.running_now));
        adapter.addFragment(new ComingSoonFragment(), getString(R.string.coming_soon));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0)
                    visibleFragment = getString(R.string.running_now);
                else
                    visibleFragment = getString(R.string.coming_soon);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_notification)
        {
            /*Intent intent = new Intent(this, CreateExamActivity.class);
            startActivity(intent);*/
            return true;
        }

        if (id == R.id.action_filter)
        {
            /*FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FilterFragment fragment = new FilterFragment();
            fragmentTransaction.add(android.R.id.content, fragment);
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
            Intent intent = new Intent(this, FilterActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.VISIBLE_FRAG, visibleFragment);
            intent.putExtra(AppConstants.LANDING_STUDENT, bundle);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_profile)
        {
            intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
            item.setChecked(false);
        }
        else if(id == R.id.nav_my_exam)
        {
            intent = new Intent(this, MyExamActivity.class);
            startActivity(intent);
            item.setChecked(false);
        }
        else if(id == R.id.nav_my_submitted_exam)
        {
            intent = new Intent(this, MySubmittedExam.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello there,\n");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if(id == R.id.nav_rate_app)
        {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "https://play.google.com/store/apps/"));
            startActivity(intent);
        }
        else if(id == R.id.nav_logout)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
