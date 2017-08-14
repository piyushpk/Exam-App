package com.examapplication.ui.activities;

import android.content.Context;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examapplication.R;
import com.examapplication.ui.fragments.ComingSoonFragment;
import com.examapplication.ui.fragments.RunningNowFragment;
import com.examapplication.utility.AppConstants;
import com.examapplication.utility.AppPreferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LandingStudentActivity extends ParentActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private Context mContext;
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
    private String userFirstName = "", userEmail = "", userToken = "", userImage = "";
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_student);
        mContext = this;

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

        userFirstName = AppPreferences.getInstance(mContext).getString(AppConstants.USER_FIRST_NAME, "");
        userToken = AppPreferences.getInstance(mContext).getString(AppConstants.TOKEN, "");
        userEmail = AppPreferences.getInstance(mContext).getString(AppConstants.USER_EMAIL, "");
        userImage = AppPreferences.getInstance(mContext).getString(AppConstants.USER_IMAGE, "");

        txtFullName = (TextView)header.findViewById(R.id.txt_full_name);
        txtEmail = (TextView)header.findViewById(R.id.txt_email);
        imgProfile = (CircleImageView)header.findViewById(R.id.img_profile);
        if(userImage.equals("") || userImage.isEmpty() || userImage == null)
        {}
        else
        {
            Picasso.with(mContext)
                   .load(userImage)
                   .into(imgProfile);
        }

        if(userToken.equals(""))
        {
            txtFullName.setText(getString(R.string.hello_guest));
            txtEmail.setText(userEmail);
            isLogin = false;
        }
        else
        {
            txtFullName.setText("Hi "+userFirstName);
            txtEmail.setText(userEmail);
            isLogin = true;
        }

        // get menu from navigationView
        Menu menu = navigationView.getMenu();

        MenuItem nav_logout = menu.findItem(R.id.nav_logout);

        // set new title to the MenuItem
        if(isLogin)
            nav_logout.setTitle(getString(R.string.logout));
        else
            nav_logout.setTitle(getString(R.string.login));

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
            intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
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
            if(isLogin)
            {
                intent = new Intent(this, EditProfileActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(mContext, getString(R.string.to_use_this_login_first), Toast.LENGTH_SHORT).show();
            }
        }
        else if(id == R.id.nav_my_exam)
        {
            if(isLogin)
            {
                intent = new Intent(this, MyExamActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(mContext, getString(R.string.to_use_this_login_first), Toast.LENGTH_SHORT).show();
            }
        }
        else if(id == R.id.nav_my_submitted_exam)
        {
            if(isLogin)
            {
                intent = new Intent(this, MySubmittedExam.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(mContext, getString(R.string.to_use_this_login_first), Toast.LENGTH_SHORT).show();
            }
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
            if(isLogin)
            {
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_FIRST_NAME, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_LAST_NAME, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_IMAGE, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_EMAIL, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_MOBILE, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_ADDRESS, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_STATE, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.USER_CITY, "");
                AppPreferences.getInstance(mContext).putString(AppConstants.TOKEN, "");
                intent = new Intent(this, SendToLoginActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                intent = new Intent(this, SendToLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
