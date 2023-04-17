package com.example.demoproject_levelup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.demoproject_levelup.Adapters.AdapterViewPager
import com.example.demoproject_levelup.Fragments.HomeFragment
import com.example.demoproject_levelup.Fragments.NotificationFragment
import com.example.demoproject_levelup.Fragments.SettingFragment
import com.example.demoproject_levelup.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationBadge: BadgeDrawable
    private lateinit var fragmentList:MutableList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        

        // Setting up Bottom Navigation
        navSetUp()



    }

//    private fun loadFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.commit()
//    }

    private fun navSetUp() {


        fragmentList = mutableListOf<Fragment>()
        fragmentList.add(HomeFragment(this))
        fragmentList.add(NotificationFragment(this))
        fragmentList.add(SettingFragment(this))

        var adapterViewPager = AdapterViewPager(this,fragmentList)
        // set Adapter
        binding.pagerMain.adapter = adapterViewPager
        binding.pagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Called when a new page has been selected
                when(position){
                    0 -> {
                        binding.bottomNavigation.selectedItemId = R.id.menu_home
                    }
                    1 -> {
                        binding.bottomNavigation.selectedItemId = R.id.menu_notification
                    }
                    2 -> {
                        binding.bottomNavigation.selectedItemId = R.id.menu_setting
                    }

                }
                super.onPageSelected(position)
            }

        })

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    findViewById<View>(R.id.home_bar).visibility = View.VISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =  View.INVISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.INVISIBLE
                    binding.pagerMain.setCurrentItem(0)
                    true
                }
                R.id.menu_notification -> {
                    findViewById<View>(R.id.home_bar).visibility = View.INVISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =   View.VISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.INVISIBLE
                    binding.pagerMain.setCurrentItem(1)
                    true
                }
                R.id.menu_setting -> {
                    findViewById<View>(R.id.home_bar).visibility = View.INVISIBLE
                    findViewById<View?>(R.id.notification_bar).visibility  =  View.INVISIBLE
                    findViewById<View?>(R.id.setting_bar).visibility = View.VISIBLE
                    binding.pagerMain.setCurrentItem(2)
                    true
                }
                else -> false
            }
        }
        val notificationItem = binding.bottomNavigation.menu.findItem(R.id.menu_notification)

        notificationBadge = binding.bottomNavigation.getOrCreateBadge(notificationItem.itemId)
        notificationBadge.isVisible = true
        notificationBadge.backgroundColor = ContextCompat.getColor(this, R.color.blue2)
    }
}
