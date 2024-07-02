package com.example.scheduling_production

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.scheduling_production.fragments.HomeFragment
import com.example.scheduling_production.fragments.ProfileFragment
import com.example.scheduling_production.fragments.ServiceFragment
import com.example.scheduling_production.fragments.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavbar: BottomNavigationView = findViewById(R.id.bottom_navbar_button)
        val floatBottom: FloatingActionButton = findViewById(R.id.floating_button)

        makeCurrentFragment(HomeFragment())

        // Bottom Navibar
        bottomNavbar.setOnItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.task -> makeCurrentFragment(HomeFragment())
                R.id.service -> makeCurrentFragment(ServiceFragment())
                R.id.user -> makeCurrentFragment(UserFragment())
                R.id.profile -> makeCurrentFragment(ProfileFragment())
            }
            true
        }
        floatBottom.setOnClickListener { showBottomDialog() }
    }

    private fun showBottomDialog() {
        TODO("Not yet implemented")
    }

    private fun makeCurrentFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_navbar, fragment)
        fragmentTransaction.commit()
    }

}