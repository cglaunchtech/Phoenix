package com.example.sportssociallayouts.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.sportssociallayouts.R
import com.example.sportssociallayouts.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_bottom_nav_main.view.*


class BottomNavMain : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
        setupWithNavController(binding.root.bottomNavigationView,navController)
    }
}