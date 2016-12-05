package com.apphousebd.austhub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    //index number to get track of the nav items
    public static int index = 0;
    protected DrawerLayout mBaseDrawer;
    protected FrameLayout mBaseFrame;
    protected NavigationView mBaseNavigationView;
    protected Toolbar mBaseToolbar;
    protected ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mBaseToolbar);

    }

    /***********************************************************************************
     * overloading the default content view method so that it can take the activity's
     * default layout and then add it to the framelayout of the base drawer layout.
     * as a result the activity's layout will become the child layout of the drawer/base
     * layout and it will have the navigation view in it.
     ************************************************************************************/
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_base, null);

        mBaseDrawer = (DrawerLayout) view.findViewById(R.id.base_drawer);

        mBaseNavigationView = (NavigationView) view.findViewById(R.id.base_nav);

        mBaseFrame = (FrameLayout) view.findViewById(R.id.base_frame_container);

        //setting the frame layout of the base layout as the parent of the activity's layout
        getLayoutInflater().inflate(layoutResID, mBaseFrame, true);

        super.setContentView(mBaseDrawer);

    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            mBaseDrawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    //change the item section of the navigation view
    protected void changeSelectedItem(int index) {

        if (!mBaseNavigationView.getMenu().getItem(index).isChecked()) {
            mBaseNavigationView.getMenu().getItem(index).setChecked(true);
        } else {
            mBaseNavigationView.getMenu().getItem(index).setChecked(false);
        }
    }

    //setting the hamburger icon on the actionbar/toolbar
    protected void setIconForDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mBaseDrawer, mBaseToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mBaseDrawer.addDrawerListener(mActionBarDrawerToggle);


        //changing the nav selected item
        changeSelectedItem(index);

        //navigation click event handle
        mBaseNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //changing the nav selected item
                changeSelectedItem(index);

                switch (item.getItemId()) {
                    case R.id.action_routine:
                        Toast.makeText(BaseActivity.this, "Routine", Toast.LENGTH_SHORT).show();
                        index = 0;
                        break;
                    case R.id.action_cpga:
                        Toast.makeText(BaseActivity.this, "CGPA", Toast.LENGTH_SHORT).show();
                        index = 1;
                        break;
                    case R.id.action_alarm:
                        Toast.makeText(BaseActivity.this, "Set Alarm", Toast.LENGTH_SHORT).show();
                        index = 2;
                        break;
                    case R.id.action_settings:
                        Toast.makeText(BaseActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        index = 3;
                        break;
                    case R.id.action_us:
                        Toast.makeText(BaseActivity.this, "About us", Toast.LENGTH_SHORT).show();
                        index = 3;
                        break;

                    default:
                        index = 0;
                        break;
                }

                //changing the nav selected item
                changeSelectedItem(index);

                Toast.makeText(BaseActivity.this, "index: " + index, Toast.LENGTH_SHORT).show();

                mBaseDrawer.closeDrawers();

                return true;
            }
        });

    }


    protected void setBaseToolbar(Toolbar toolbar) {
        mBaseToolbar = toolbar;
        setSupportActionBar(mBaseToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //drawer checking
    protected boolean isDrawerOpen() {
        return mBaseDrawer != null && mBaseDrawer.isDrawerOpen(GravityCompat.START);
    }

}
