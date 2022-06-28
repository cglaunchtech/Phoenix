package com.example.sportssocial.ui.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.sportssocial.MainActivity
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.SportsRepository
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_user_profile.*

class UpdateUserProfileActivity : AppCompatActivity() {

    val repo: SportsRepository by lazy { SportsRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_profile)


        var id: TextView = findViewById(R.id.id_t)
        var updateCity: TextInputEditText = findViewById(R.id.updateCityfield)
        var updateState: TextInputEditText = findViewById(R.id.updateStatefield)
        var updateBirthday: TextInputEditText = findViewById(R.id.updateBirthdayfield)
        var updateAboutme: TextInputEditText = findViewById(R.id.updateAboutmeField)
        var updateSportsautocomplete: AutoCompleteTextView = findViewById(R.id.updateSportsautocomplete)
        var updateSportsautocompleteSecond: AutoCompleteTextView = findViewById(R.id.updateSportsautocompleteSecond)
        var updateTitleautocomplete: AutoCompleteTextView = findViewById(R.id.updateTitleautocomplete)
        var updateTitleautocompleteSecond: AutoCompleteTextView = findViewById(R.id.updateTitleautocompleteSecond)
        var updateProfilebutton : Button = findViewById(R.id.updateProfilebutton)


        id.setText(intent.getIntExtra("Id", 0).toString())
        updateCity.setText(intent.getStringExtra("updateCity").toString())
        updateState.setText(intent.getStringExtra("updateState").toString())
        updateBirthday.setText(intent.getStringExtra("updateBirthday").toString())
        updateAboutme.setText(intent.getStringExtra("updateAboutme").toString())
        updateSportsautocomplete.setText(intent.getStringExtra("updateSportsautocomplete").toString())
        updateSportsautocompleteSecond.setText(intent.getStringExtra("updateSportsautocompleteSecond").toString())
        updateTitleautocomplete.setText(intent.getStringExtra("updateTitleautocomplete").toString())
        updateTitleautocompleteSecond.setText(intent.getStringExtra("updateSportsautocompleteSecond").toString())



        updateProfilebutton.setOnClickListener {
            repo.updateAthlete(
                Athlete(
                        id.text.toString().toInt()
                            updateCity.setText(intent.getStringExtra("updateCity").toString())
                            updateState.setText(intent.getStringExtra("updateState").toString())
                            updateBirthday.setText(intent.getStringExtra("updateBirthday").toString())
                            updateAboutme.setText(intent.getStringExtra("updateAboutme").toString())
                            updateSportsautocomplete.setText(intent.getStringExtra("updateSportsautocomplete").toString())
                            updateSportsautocompleteSecond.setText(intent.getStringExtra("updateSportsautocompleteSecond").toString())
                            updateTitleautocomplete.setText(intent.getStringExtra("updateTitleautocomplete").toString())
                            updateTitleautocompleteSecond.setText(intent.getStringExtra("updateSportsautocompleteSecond").toString())
                )
            )
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }


        var updateProfilePicture: CardView = findViewById(R.id.updateProfilePicture)
       updateProfilePicture.bringToFront()
       updateProfilePicture.setOnClickListener() {
//
//
//            val NextIntent = Intent(this, CameraActivity::class.java)
//            startActivity(NextIntent)
 }

        updateFields()

        }
        private fun updateFields(){
            updateProfilebutton.setOnClickListener {

                if (TextUtils.isEmpty( updateCityfield.text.toString())){
                    updateCityfield.setError("City Required")
                    return@setOnClickListener

                }else if (TextUtils.isEmpty( updateStatefield.text.toString())){
                    updateStatefield.setError("State Required")
                    return@setOnClickListener

                }else if (TextUtils.isEmpty( updateBirthdayfield.text.toString())){
                    updateBirthdayfield.setError("Date of Birth Required")
                    return@setOnClickListener

                } else if (TextUtils.isEmpty(updateSportsautocomplete.text.toString())) {
                    updateSportsautocomplete.setError("At Least One Sport is Required")
                    return@setOnClickListener

                } else if (TextUtils.isEmpty(updateTitleautocomplete.text.toString())) {
                    updateTitleautocomplete.setError("At Least One Title or Position is Required")
                    return@setOnClickListener
                }
                println("Please complete all required fields before updating profile.")

                   }
                }
            }



