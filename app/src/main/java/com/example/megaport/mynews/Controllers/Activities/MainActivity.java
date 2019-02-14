package com.example.megaport.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.megaport.mynews.Controllers.Fragments.MainFragment;
import com.example.megaport.mynews.Controllers.Fragments.fragmentPagerAdapter;
import com.example.megaport.mynews.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    // FOR RECYCLERVIEW
    private MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
    public static int tabPos = 0;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();


        if(savedInstanceState == null) {
            this.configureAndShowMainFragment();
        }
        else {
            mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        }

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(new fragmentPagerAdapter(getSupportFragmentManager()
        ));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                mViewPager.setCurrentItem(tab.getPosition());
                tabPos = tab.getPosition();
                mainFragment.executeHttpRequestWithRetrofit();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mainFragment.executeHttpRequestWithRetrofit();
            }
        });
    }

    // --------
    // TOOLBAR
    // --------

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_main_toolbar_search:
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.activity_main_toolbar_help:
                Intent intentHelp = new Intent( this, HelpActivity.class );
                startActivity(intentHelp);
                break;
            case R.id.activity_main_toolbar_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            default:
                break;
        }

        return true;
    }

    // -------------

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // -------------------
    // NAVIGATION DRAWER
    // -------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_drawer_top_stories :
                mViewPager.setCurrentItem( 0 );
                break;
            case R.id.activity_main_drawer_most_popular:
                mViewPager.setCurrentItem( 1 );
                break;
            case R.id.activity_main_drawer_notification:
                Intent intentNotification = new Intent(this, NotificationsActivity.class);
                startActivity(intentNotification);
                break;
            case R.id.activity_main_drawer_help:
                Intent intentHelp = new Intent( this, HelpActivity.class );
                startActivity(intentHelp);
                break;
            case R.id.activity_main_drawer_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        NavigationView navigationView = findViewById( R.id.activity_main_nav_view );
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureAndShowMainFragment(){
        if (mainFragment == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit();
        }
    }

}