package com.apphousebd.austhub;

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
import android.view.View;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity {

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
        overloading the default content view method so that it can take the activity's
     default layout and then add it to the framelayout of the base drawer layout.
     as a result the activity's layout will become the child layout of the drawer/base
     layout and it will have the navigation view in it.
    ************************************************************************************/
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_base, null);

        mBaseDrawer = (DrawerLayout) view.findViewById(R.id.base_drawer);

        mBaseNavigationView = (NavigationView) mBaseDrawer.findViewById(R.id.base_nav);

        mBaseFrame = (FrameLayout) view.findViewById(R.id.base_frame_container);

        mBaseNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                mBaseDrawer.closeDrawers();

                return true;
            }
        });


        //setting the frame layout of the base layout as the parent of the activity's layout
        getLayoutInflater().inflate(layoutResID,mBaseDrawer,true);

        super.setContentView(mBaseDrawer);
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen())
        {
            mBaseDrawer.closeDrawers();
        }
        else {
            super.onBackPressed();
        }
    }

    //setting the hamburger icon on the actionbar/toolbar
    protected void setIconForDrawer()
    {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mBaseDrawer, mBaseToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mBaseDrawer.addDrawerListener(mActionBarDrawerToggle);
    }


    protected void setBaseToolbar(Toolbar toolbar)
    {
        mBaseToolbar = toolbar;
        setSupportActionBar(mBaseToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //drawer checking
    protected boolean isDrawerOpen()
    {
        return mBaseDrawer != null && mBaseDrawer.isDrawerOpen(GravityCompat.START);
    }

}
