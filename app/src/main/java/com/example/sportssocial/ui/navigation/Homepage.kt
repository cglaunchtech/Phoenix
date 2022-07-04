package com.example.sportssocial.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.sportssocial.R
import com.example.sportssocial.databinding.ActivityBottomNavMainBinding

class Homepage : AppCompatActivity() {

    private lateinit var binding:ActivityBottomNavMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        binding = ActivityBottomNavMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

    }
}