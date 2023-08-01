package com.sempleprojectbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.sempleprojectbase.databinding.ActivityMainBinding

class MainActivity :  BaseActivity<ActivityMainBinding>(R.layout.activity_main)  {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavController()

    }
    private fun initNavController() {
        navController =
            (supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment).navController
        //binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
    }
}