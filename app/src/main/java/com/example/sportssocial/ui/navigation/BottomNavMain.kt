package com.example.sportssocial.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController

import com.example.sportssocial.R
import com.example.sportssocial.databinding.ActivityBottomNavMainBinding


class BottomNavMain : AppCompatActivity() {

    private lateinit var binding:ActivityBottomNavMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
        setupWithNavController(binding.bottomNavigationView,navController)
    }
}
