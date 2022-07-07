package com.example.sportssocial.ui.view

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.view.camera.ProfilePhotoCapture
import com.example.sportssocial.util.Constants.Companion.FIRESTORE
import com.example.sportssocial.util.Constants.Companion.STORAGE
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.lang.Exception
import java.util.*



class SignUp : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    var databaseReference : DatabaseReference? =null
    var database : FirebaseDatabase? = null
    lateinit var firestoreRepo : FirestoreRepo
    var profilePicUrl : String? = null

    lateinit var addProfilepic: ShapeableImageView
    lateinit var firstNameField : TextInputEditText
    lateinit var lastNameField : TextInputEditText
    lateinit var usernameField : TextInputEditText
    lateinit var emailField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var confirmPassword: TextInputEditText
    lateinit var cityField : TextInputEditText
    lateinit var stateField : TextInputEditText
    lateinit var birthdayField : TextInputEditText
    lateinit var aboutMeField : TextInputEditText
    lateinit var sportsSelection : MaterialAutoCompleteTextView
    lateinit var sportsSelectionTwo : MaterialAutoCompleteTextView

    lateinit var submitButton : Button
    lateinit var cancelButton : Button
    var profilePhotostr: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_up)

        addProfilepic = findViewById(R.id.editAddProfilePicture)
        //firstNameField = findViewById(R.id.editFirstName1)
       // lastNameField  = findViewById(R.id.lastName1)
        usernameField = findViewById(R.id.username2)
        emailField  = findViewById(R.id.emailField2)
        passwordField = findViewById(R.id.passwordField2)
        confirmPassword = findViewById(R.id.confirmPasswordField2)
        cityField = findViewById(R.id.cityField2)
        stateField = findViewById(R.id.stateField2)
        birthdayField = findViewById(R.id.birthdayField2)
        aboutMeField = findViewById(R.id.aboutMeField2)
        sportsSelection = findViewById(R.id.editSportsAutocomplete)
        sportsSelectionTwo = findViewById(R.id.editSportsAutocompleteSecond)

        submitButton = findViewById(R.id.submitButton)
        cancelButton = findViewById(R.id.cancelButton)

        auth = FirebaseAuth.getInstance()
        firestoreRepo = FirestoreRepo()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")



        register()

        var profilePhoto: ImageView = findViewById(R.id.editAddProfilePicture)
        profilePhotostr= intent.getStringExtra("profilePhoto")
        //decode base64 string
        if (profilePhotostr != null) {

            var bytes: ByteArray = Base64.decode(profilePhotostr, Base64.DEFAULT);
            // Initialize bitmap
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            // set bitmap on imageView
            profilePhoto.setImageBitmap(bitmap)
        }
        cancelButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        addProfilepic.bringToFront()
        addProfilepic.setOnClickListener() {

            val NextIntent = Intent(this, ProfilePhotoCapture::class.java)
            startActivity(NextIntent)
        }
    }
    private fun register(){
        submitButton.setOnClickListener {

            if(TextUtils.isEmpty( firstNameField.text.toString())){
                firstNameField.setError("Please Enter First Name")
                return@setOnClickListener

            }else if(TextUtils.isEmpty( lastNameField.text.toString())){
                lastNameField.setError("Please Enter Last Name")
                return@setOnClickListener

            }else if(TextUtils.isEmpty( usernameField.text.toString())){
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

            }
            println("Please complete all fields")

            auth.createUserWithEmailAndPassword(emailField.text.toString(),passwordField.text.toString())
                .addOnSuccessListener {
                    Log.d("AppDatabase","AAA to 1")
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                    firestoreRepo.uploadToFirebase(intent.getParcelableExtra<Uri>("profilePhotoUri"),
                        Athlete(
                            id = null,
                            uid =  auth.uid,
                            username = usernameField.text.toString(),
                            profilePhoto = profilePicUrl,
                            first = firstNameField.text.toString(),
                            last = lastNameField.text.toString(),
                            city = cityField.text.toString(),
                            state = stateField.text.toString(),
                            dob = birthdayField.text.toString(),
                            aboutMe = aboutMeField.text.toString(),
                            sport1 = sportsSelection.text.toString(),
                            sport2 = sportsSelection.text.toString(),
                            photoCollection = mutableListOf(),
                            highlightVideos = mutableListOf(),
                            following = mutableListOf()
                        ))
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Log.d("AppDatabase","AAA else 1")
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(it.message)
                    builder.setCancelable(true)
                    builder.setNegativeButton("OK", DialogInterface.OnClickListener
                        { dialog, which -> dialog.cancel() })
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.show()
                        Toast.makeText(this, "Registration Failed; Please Try Again", Toast.LENGTH_LONG).show()
                }
        }
    }
}


