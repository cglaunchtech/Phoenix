package com.example.sportssociallayouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sportssociallayouts.ui.navigation.BottomNavMain

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            startActivity(Intent(this,BottomNavMain::class.java))
        }
    }
}