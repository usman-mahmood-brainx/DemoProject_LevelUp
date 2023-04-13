package com.example.demoproject_levelup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var notificationBadge: BadgeDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this))

        // Setting up Bottom Navigation
        navSetUp()

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    private fun navSetUp() {

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    findViewById<View>(R.id.home_bar).visibility = View.VISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =  View.INVISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.INVISIBLE

                    true
                }
                R.id.menu_notification -> {
                    findViewById<View>(R.id.home_bar).visibility = View.INVISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =   View.VISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.INVISIBLE
                    true
                }
                R.id.menu_setting -> {
                    findViewById<View>(R.id.home_bar).visibility = View.INVISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =  View.INVISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.VISIBLE
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