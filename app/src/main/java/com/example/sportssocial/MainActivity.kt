package com.example.sportssocial

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.example.sportssocial.ui.navigation.BottomNavMain

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.crashButton).setOnClickListener {
                startActivity(Intent(this,BottomNavMain::class.java)) }

//        val btn:Button = findViewById(R.id.submitButton)
//        btn.setOnClickListener {
//            val intento = Intent(this, BottomNavMain::class.java)
//            startActivity(intento)
//        }

//        sellBtn.setOnClickListener {
//            val intentNext = Intent(this,SellMain::class.java)
//            startActivity(intentNext)
//            println("Main plus button Pressed")
//        }
    }
}