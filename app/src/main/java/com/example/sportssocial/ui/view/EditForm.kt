package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.SportsRepository
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_edit_form.*


class EditForm : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_form)

    }
}

