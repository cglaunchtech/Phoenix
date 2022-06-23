package com.example.sportssociallayouts.ui.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sportssociallayouts.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText

class RegistrationForm : AppCompatActivity() {

//    lateinit var auth : FirebaseAuth
//    var databaseReference : DatabaseReference? =null
//    var database : FirebaseDatabase? = null

    lateinit var usernameField : TextInputEditText
    lateinit var emailField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var confirmPassword: TextInputEditText
    lateinit var cityField : TextInputEditText
    lateinit var stateField : TextInputEditText
    lateinit var birthdayField : TextInputEditText
    lateinit var aboutMeField : TextInputEditText
    lateinit var sportsSelection : TextInputEditText
    lateinit var sportsSelectionTwo : TextInputEditText
    lateinit var titleSelection : TextInputEditText
    lateinit var titleSelectionTwo : TextInputEditText

    //lateinit var sign_up : Button
    // lateinit var cancel_btn : Button

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
        sportsSelection = findViewById(R.id.sportsSelection)
        sportsSelectionTwo = findViewById(R.id.sportsSelectionTwo)
        titleSelection = findViewById(R.id.titleSelection)
        titleSelectionTwo = findViewById(R.id.titleSelectionTwo)

        //sign_up = findViewById(R.id.sign_up)
        //cancel_btn = findViewById(R.id.cancel_btn)
//            auth = FirebaseAuth.getInstance()
//            database = FirebaseDatabase.getInstance()
//            databaseReference = database?.reference!!.child("profile")

        register()

    }
    private fun register(){
        sign_up.setOnClickListener {

            if(TextUtils.isEmpty( usernameField.text.toString())){
                usernameField.setError("Please Enter User Name")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( emailField.text.toString())){
                emailField.setError("Please Enter Your Email Address")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( passwordField.text.toString())){
                passwordField.setError("Please Enter Password")
                return@setOnClickListener
            }else if (TextUtils.isEmpty( confirmPassword.text.toString())){
                confirmPassword.setError("Please Confirm Password")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( cityField.text.toString())){
                cityField.setError("Please Enter Your City")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( stateField.text.toString())){
                stateField.setError("Please Enter Your State of Residency")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( birthdayField.text.toString())){
                birthdayField.setError("Please Enter Your Date of Birth")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( sportsSelection.text.toString())){
                sportsSelection.setError("Please Select at Least One Sport")
                return@setOnClickListener

            }else if (TextUtils.isEmpty( titleSelection.text.toString())){
                titleSelection.setError("Please Select Your Current Title or Position")
                return@setOnClickListener
            }
            println("Please complete all fields")

            auth.createUserWithEmailAndPassword(emailField.text.toString(),passwordField.text.toString())
                .addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d("AppDatabase","AAA to 1")
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
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
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
        }
//            cancel_btn.setOnClickListener {
//                val intent = Intent(this, LandingPage::class.java)
//                startActivity(intent)

    }
}


