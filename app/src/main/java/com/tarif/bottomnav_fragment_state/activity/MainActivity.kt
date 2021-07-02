package com.tarif.bottomnav_fragment_state.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tarif.bottomnav_fragment_state.ui.GalleryFragment
import com.tarif.bottomnav_fragment_state.ui.HomeFragment
import com.tarif.bottomnav_fragment_state.ui.NotificationFragment
import com.tarif.bottomnav_fragment_state.R
import com.tarif.bottomnav_fragment_state.databinding.ActivityMainBinding
import com.tarif.bottomnav_fragment_state.viewpager.CustomViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)


        /**
         *
         * we request viewpager touch call back to disable swipe gesture
         * for swipe enable enable set true or remove this line
         */
        bind.viewPager.isUserInputEnabled = false


        /**
         * bottom navigation select listener
         * here setCurrentItem(position,smoothScroll = false) , then changing fragment with no transition
         * If you set smoothScroll true then changing fragment with sliding transition
         *
         * */
        bind.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    bind.viewPager.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_gallery -> {
                    bind.viewPager.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_notification -> {
                    bind.viewPager.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }


        /**
         * After changing fragment find the fragment position and
         * set selected item bottom navigation Item
         *
         * */
        bind.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> bind.bottomNavigation.menu.findItem(R.id.nav_home).isChecked = true
                    1 -> bind.bottomNavigation.menu.findItem(R.id.nav_gallery).isChecked = true
                    2 -> bind.bottomNavigation.menu.findItem(R.id.nav_notification).isChecked = true
                }
            }
        })


        /**
         * Set up View Pager with View Pager Adapter
         * */
        val viewPagerAdapter = CustomViewPager(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(HomeFragment())
        viewPagerAdapter.addFragment(GalleryFragment())
        viewPagerAdapter.addFragment(NotificationFragment())
        bind.viewPager.adapter = viewPagerAdapter


    }

    override fun onBackPressed() {
        val currentFragment = bind.viewPager.currentItem
        if (currentFragment != 0) {
            bind.viewPager.setCurrentItem(0, false)
            return
        }
        super.onBackPressed()
    }
}