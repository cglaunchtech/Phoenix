package com.example.sportssocial.ui.view

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
import androidx.cardview.widget.CardView
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_user_profile.*

class UpdateUserProfileActivity : AppCompatActivity() {

    lateinit var vm: MainViewModel
    lateinit var auth : FirebaseAuth
    var databaseReference : DatabaseReference? =null
    var database : FirebaseDatabase? = null

    var updateUsernamefield : TextInputEditText = findViewById(R.id.updateUsernamefield)
    var updateCityfield : TextInputEditText = findViewById(R.id.updateCityfield)
    var updateStatefield : TextInputEditText = findViewById(R.id.updateStatefield)
    var updateBirthdayfield : TextInputEditText = findViewById(R.id.updateBirthdayfield)
    var updateAboutmeField : TextInputEditText = findViewById(R.id.updateAboutmeField)
    var updateSportsautocomplete: AutoCompleteTextView = findViewById(R.id.updateSportsautocomplete)
    var updateSportsautocompleteSecond: AutoCompleteTextView = findViewById(R.id.updateSportsautocompleteSecond)
    var updateTitleautocomplete: AutoCompleteTextView = findViewById(R.id.updateTitleautocomplete)
    var updateTitleautocompleteSecond: AutoCompleteTextView = findViewById(R.id.updateTitleautocompleteSecond)
    var updateProfilebutton : Button = findViewById(R.id.updateProfilebutton)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_profile)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

       var UpdateProfilePicture: CardView = findViewById(R.id.updateProfilepicture)

       updateProfilepicture.bringToFront()
       updateProfilepicture.setOnClickListener() {
//
//
//            val NextIntent = Intent(this, CameraActivity::class.java)
//            startActivity(NextIntent)
 }

        updateRegistration()

        }
        private fun updateRegistration(){
            updateProfilebutton.setOnClickListener {

                if(TextUtils.isEmpty( updateUsernamefield.text.toString())){
                    updateUsernamefield.setError("Username Required")
                    return@setOnClickListener

                }else if (TextUtils.isEmpty( updateEmailfield.text.toString())){
                    updateEmailfield.setError("Email Address Required")
                    return@setOnClickListener

                }else if (TextUtils.isEmpty( updatePasswordfield.text.toString())){
                    updatePasswordfield.setError("Password Required")
                    return@setOnClickListener
//            }else if (TextUtils.isEmpty( updateConfirmpassword.text.toString())){
//                confirmPassword.setError("Please Confirm Password Update")
//                return@setOnClickListener

                }else if (TextUtils.isEmpty( updateCityfield.text.toString())){
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

                auth.createUserWithEmailAndPassword(
                    updateEmailfield.text.toString(),
                    updatePasswordfield.text.toString()
                )
                    .addOnCompleteListener(this, OnCompleteListener{ task ->
                        if(task.isSuccessful){

                            vm.insertAthlete(
                                Athlete(
                                    null,
                                    updateProfilepicture.toString(),
                                    updateUsernamefield.text.toString(),
                                    updateEmailfield.text.toString(),
                                    updateCityfield.text.toString(),
                                    updateStatefield.text.toString(),
                                    updateBirthdayfield.text.toString(),
                                    updateAboutmeField.text.toString(),
                                    updateSportsautocomplete.text.toString(),
                                  //updateSportsautocompleteSecond.text.toString(),
                                  //updateTitleautocomplete.text.toString(),
                                  // updateTitleautocompleteSecond.text.toString()
                                )
                            )

                            Log.d("AppDatabase","AAA to 1")
                            Toast.makeText(this, "Profile Updated Sucessfully", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else {
                            Log.d("AppDatabase","AAA else 1")
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage("User Already Exists. Login with a different Email and Password or Register with another Email Address")
                            builder.setCancelable(true)
                            builder.setNegativeButton("OK", DialogInterface.OnClickListener
                            { dialog, which -> dialog.cancel() })
                            val alertDialog: AlertDialog = builder.create()
                            alertDialog.show()
                            Toast.makeText(
                                this, "Profile Update Failed; Please Try Again",
                                Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }
    }

