package com.example.demoproject_levelup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var notificationBadge: BadgeDrawable
    private lateinit var pagerMain:ViewPager2
    private lateinit var fragmentList:MutableList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        // Setting up Bottom Navigation
        navSetUp()



    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    private fun navSetUp() {

        pagerMain = findViewById<ViewPager2>(R.id.pagerMain)

        fragmentList = mutableListOf<Fragment>()
        fragmentList.add(HomeFragment(this))
        fragmentList.add(NotificationFragment(this))
        fragmentList.add(SettingFragment(this))

        var adapterViewPager = AdapterViewPager(this,fragmentList)
        // set Adapter
        pagerMain.adapter = adapterViewPager
        pagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Called when a new page has been selected
                when(position){
                    0 -> {
                        bottomNavigationView.selectedItemId = R.id.menu_home
                    }
                    1 -> {
                        bottomNavigationView.selectedItemId = R.id.menu_notification
                    }
                    2 -> {
                        bottomNavigationView.selectedItemId = R.id.menu_setting
                    }

                }
                super.onPageSelected(position)
            }

        })

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    findViewById<View>(R.id.home_bar).visibility = View.VISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =  View.INVISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.INVISIBLE
                    pagerMain.setCurrentItem(0)
                    true
                }
                R.id.menu_notification -> {
                    findViewById<View>(R.id.home_bar).visibility = View.INVISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =   View.VISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.INVISIBLE
                    pagerMain.setCurrentItem(1)
                    true
                }
                R.id.menu_setting -> {
                    findViewById<View>(R.id.home_bar).visibility = View.INVISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =  View.INVISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.VISIBLE
                    pagerMain.setCurrentItem(2)
                    true
                }
                else -> false
            }
        }
        val notificationItem = bottomNavigationView.menu.findItem(R.id.menu_notification)

        notificationBadge = bottomNavigationView.getOrCreateBadge(notificationItem.itemId)
        notificationBadge.isVisible = true
        notificationBadge.backgroundColor = ContextCompat.getColor(this, R.color.blue2)
    }
}
