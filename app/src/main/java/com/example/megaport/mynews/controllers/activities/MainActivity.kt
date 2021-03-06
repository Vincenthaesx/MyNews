package com.example.megaport.mynews.controllers.activities

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
import com.example.megaport.mynews.controllers.fragments.FragmentPagerAdapter
import com.example.megaport.mynews.R
import com.example.megaport.mynews.controllers.utils.openActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerLayout: androidx.drawerlayout.widget.DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureToolBar()

        configureDrawerLayout()

        configureNavigationView()

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
                openActivity<SearchActivity>()
            }
            R.id.activity_main_toolbar_help -> {
                openActivity<HelpActivity>()
            }
            R.id.activity_main_toolbar_about -> {
                openActivity<AboutActivity>()
            }
            else -> {
                throw IllegalArgumentException("Invalide item")
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

        val id = item.itemId

        when (id) {

            R.id.activity_main_drawer_arts -> {
                navigateToArticleList("arts")
            }
            R.id.activity_main_drawer_technology-> {
                navigateToArticleList("technology")
            }
            R.id.activity_main_drawer_automobiles-> {
                navigateToArticleList("automobiles")
            }
            R.id.activity_main_drawer_food-> {
                navigateToArticleList("food")
            }
            R.id.activity_main_drawer_movies-> {
                navigateToArticleList("movies")
            }
            R.id.activity_main_drawer_sports->{
                navigateToArticleList("sports")
            }
            R.id.activity_main_drawer_science-> {
                navigateToArticleList("science")
            }
            R.id.activity_main_drawer_books-> {
                navigateToArticleList("books")
            }
            R.id.activity_main_drawer_world-> {
                navigateToArticleList("world")
            }
            R.id.activity_main_drawer_magazine->{
                navigateToArticleList("magazine")
            }

            // ---------------------------------------------

            R.id.activity_main_drawer_notification -> {
                openActivity<NotificationsActivity>()
            }
            R.id.activity_main_drawer_help -> {
                openActivity<HelpActivity>()
            }
            R.id.activity_main_drawer_about -> {
                openActivity<AboutActivity>()
            }
            else -> {
            }
        }

        this.drawerLayout!!.closeDrawer(GravityCompat.START)

        return true
    }

    private fun navigateToArticleList(section:String){
        val intent = Intent(this, ArticleListActivity::class.java)
        intent.putExtra(SECTION,section)
        startActivity(intent)
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
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
    }

    // 3 - Configure NavigationView
    private fun configureNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.activity_main_nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    companion object{
        private const val SECTION = "section"

    }

}