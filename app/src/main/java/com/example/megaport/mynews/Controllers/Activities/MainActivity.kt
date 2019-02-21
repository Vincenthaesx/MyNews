package com.example.megaport.mynews.Controllers.Activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.megaport.mynews.Controllers.Fragments.FragmentPagerAdapter
import com.example.megaport.mynews.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerLayout: androidx.drawerlayout.widget.DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configureToolBar()

        this.configureDrawerLayout()

        this.configureNavigationView()


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewpager.adapter = FragmentPagerAdapter(supportFragmentManager)

        // Give the TabLayout the ViewPager
        val tabLayout = findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(viewpager)
    }

    // --------
    // TOOLBAR
    // --------

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity_main_toolbar_search -> {
                val intentSearch = Intent(this, SearchActivity::class.java)
                startActivity(intentSearch)
            }
            R.id.activity_main_toolbar_help -> {
                val intentHelp = Intent(this, HelpActivity::class.java)
                startActivity(intentHelp)
            }
            R.id.activity_main_toolbar_about -> {
                val intentAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentAbout)
            }
            else -> {
            }
        }

        return true
    }

    // -------------

    override fun onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // -------------------
    // NAVIGATION DRAWER
    // -------------------

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // 4 - Handle Navigation Item Click
        val id = item.itemId

        when (id) {
            R.id.activity_main_drawer_top_stories -> 0
            R.id.activity_main_drawer_most_popular -> 1
            R.id.activity_main_drawer_notification -> {
                val intentNotification = Intent(this, NotificationsActivity::class.java)
                startActivity(intentNotification)
            }
            R.id.activity_main_drawer_help -> {
                val intentHelp = Intent(this, HelpActivity::class.java)
                startActivity(intentHelp)
            }
            R.id.activity_main_drawer_about -> {
                val intentAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentAbout)
            }
            else -> {
            }
        }

        this.drawerLayout!!.closeDrawer(GravityCompat.START)

        return true
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    private fun configureToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    // 2 - Configure Drawer Layout
    private fun configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()
    }

    // 3 - Configure NavigationView
    private fun configureNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.activity_main_nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

}