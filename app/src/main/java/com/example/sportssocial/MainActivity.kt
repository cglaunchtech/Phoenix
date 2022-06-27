package com.example.sportssocial

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.example.sportssocial.ui.view.LoginActivity
import com.example.sportssocial.ui.view.RegistrationForm
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    lateinit var crashButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crashButton = findViewById(R.id.crashButton)


        crashButton.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }

    }
}