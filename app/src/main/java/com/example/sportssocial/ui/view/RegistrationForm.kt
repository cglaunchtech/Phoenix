package com.example.sportssocial.ui.view

import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sportssocial.MainActivity
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.db.entities.UserProfile
import com.example.sportssocial.ui.viewmodel.UserProfileViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegistrationForm : AppCompatActivity() {

      lateinit var auth : FirebaseAuth
      var databaseReference : DatabaseReference? =null
      var database : FirebaseDatabase? = null


    lateinit var usernameField : TextInputEditText
    lateinit var emailField : TextInputEditText
    lateinit var passwordField : TextInputEditText
   // lateinit var confirmPassword: TextInputEditText
    lateinit var cityField : TextInputEditText
    lateinit var stateField : TextInputEditText
    lateinit var birthdayField : TextInputEditText
    lateinit var aboutMeField : TextInputEditText
    lateinit var sportsAutocomplete: AutoCompleteTextView
    lateinit var sportsAutocompleteSecond: AutoCompleteTextView
    lateinit var titleAutocomplete: AutoCompleteTextView
    lateinit var titleAutocompleteSecond: AutoCompleteTextView
    lateinit var submitButton : Button
    lateinit var vm : UserProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)

        usernameField = findViewById(R.id.usernameField)
        emailField  = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        //confirmPassword = findViewById(R.id.confirmPassword)
        cityField = findViewById(R.id.cityField)
        stateField = findViewById(R.id.stateField)
        birthdayField = findViewById(R.id.birthdayField)
        aboutMeField = findViewById(R.id.aboutMeField)
        sportsAutocomplete = findViewById(R.id.sportsAutocomplete)
        sportsAutocompleteSecond = findViewById(R.id.sportsAutocompleteSecond)
        titleAutocomplete = findViewById(R.id.titleAutocomplete)
        titleAutocompleteSecond = findViewById(R.id.titleAutocompleteSecond)
        submitButton = findViewById(R.id.submitButton)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        vm = UserProfileViewModel(application)


        register()

    }
    private fun register(){
        submitButton.setOnClickListener {

            if(TextUtils.isEmpty( usernameField.text.toString())){
                usernameField.setError("Please Enter User Name")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( emailField.text.toString())){
                emailField.setError("Please Enter Your Email Address")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( passwordField.text.toString())){
                passwordField.setError("Please Enter Password")
                return@setOnClickListener
//            }else if (TextUtils.isEmpty( confirmPassword.text.toString())){
//                confirmPassword.setError("Please Confirm Password")
//                return@setOnClickListener

            }else if (TextUtils.isEmpty( cityField.text.toString())){
                cityField.setError("Please Enter Your City")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( stateField.text.toString())){
                stateField.setError("Please Enter Your State of Residency")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( birthdayField.text.toString())){
                birthdayField.setError("Please Enter Your Date of Birth")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(sportsAutocomplete.text.toString())) {
                sportsAutocomplete.setError("Please Include at Least One Sport")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(titleAutocomplete.text.toString())) {
                titleAutocomplete.setError("Please Include Your Current Title or Position")
                return@setOnClickListener
            }
            println("Please complete all fields")

            auth.createUserWithEmailAndPassword(
                emailField.text.toString(),
                passwordField.text.toString()
            )
                .addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d("AppDatabase","AAA to 1")
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        firestoreAthleteInit()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Log.d("AppDatabase","AAA else 1")
                        val builder = AlertDialog.Builder(this@RegistrationForm)
                        builder.setMessage("User Already Exists. Login with a different Email and Password or Register with another Email Address")
                        builder.setCancelable(true)
                        builder.setNegativeButton("OK", DialogInterface.OnClickListener
                        { dialog, which -> dialog.cancel() })
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.show()
                        Toast.makeText(
                            this, "Registration Failed; Please Try Again",
                            Toast.LENGTH_LONG).show()
                }
            })
        }
    }
    private fun firestoreAthleteInit(){
        val newProfile = UserProfile(
            auth.uid,
            Athlete(Id = null,
                username = usernameField.text.toString(),
                email = emailField.text.toString(),
                city = cityField.text.toString(),
                state = stateField.text.toString(),
                DOB = birthdayField.text.toString(),
                aboutMe =aboutMeField.text.toString(),
                sport = sportsAutocomplete.text.toString(),
                title = titleAutocomplete.text.toString()
            ),
            null,
            null
        )
        vm.insertProfiles(newProfile)

    }
}


