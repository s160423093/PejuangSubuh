package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.ui.NavigationUI
import com.ubayadev.pejuangsubuh.R
import com.ubayadev.pejuangsubuh.databinding.ActivityMainBinding
import com.ubayadev.pejuangsubuh.databinding.FragmentDashboardBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        navController = (supportFragmentManager.findFragmentById(TODO("Not yet implemented")) as NavHostController).navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}