package com.example.sportssocial.ui.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.view.camera.ProfilePhotoCapture
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class UpdateProfile : AppCompatActivity() {
    //val repo: AthleteRepository by lazy { AthleteRepository(this) }
    var databaseReference : DatabaseReference? = null
    var database : FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    lateinit var vm: MainViewModel
    lateinit var repo: FirestoreRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_edit_profile)


        var firstName1 : TextInputEditText = findViewById(R.id.editFirstName1)
        var lastName : TextInputEditText = findViewById(R.id.lastName1)
        var cityField : TextInputEditText = findViewById(R.id.cityField1)
        var stateField : TextInputEditText = findViewById(R.id.stateField2)
        var aboutMeField : TextInputEditText = findViewById(R.id.aboutMeField2)
        //var sportsAutocomplete: AutoCompleteTextView = findViewById(R.id.sportsAutocomplete1)
        //var sportsAutocompleteSecond: AutoCompleteTextView = findViewById(R.id.sportsAutocompleteSecond)
        var repo = FirestoreRepo()
        var submitButton: Button = findViewById(R.id.submitButton)
        var cancelButton: Button = findViewById(R.id.cancelButton)


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        updateRequiredfields()

        var addProfilePicture: ShapeableImageView = findViewById(R.id.editAddProfilePicture)
        var profilePhotostr= intent.getStringExtra("profilePhoto")
        if (profilePhotostr != null) {

            var bytes: ByteArray = Base64.decode(profilePhotostr, Base64.DEFAULT);
            // Initialize bitmap
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size);
            // set bitmap on imageView
            addProfilePicture.setImageBitmap(bitmap);
        }

        //editFirstName2.setText(intent.getStringExtra("first"))
        lastName.setText(intent.getStringExtra("last"))
        //cityField1.setText(intent.getStringExtra("city"))
        stateField2.setText(intent.getStringExtra("state"))
        aboutMeField.setText(intent.getStringExtra("aboutMe"))
        //sportsAutocomplete.setText(intent.getStringExtra("sport1"))
        //sportsAutocompleteSecond.setText(intent.getStringExtra("sport2"))


        submitButton.setOnClickListener {
            vm.updateAthlete(
                Athlete(
                    id = null,
                    uid = auth.uid,
                    username = null,
                    profilePhoto = null,
                    //first = editFirstName2.text.toString(),
                    last = lastName.text.toString(),
                    //city = cityField1.text.toString(),
                    state = stateField2.text.toString(),
                    dob = null,
                    aboutMe = editAboutMeField2.text.toString(),
                    //sport1 = sportsAutocomplete.text.toString(),
                    //sport2 = sportsAutocompleteSecond.text.toString(),
                    photoCollection = mutableListOf(),
                    highlightVideos = mutableListOf(),
                    following = mutableListOf()
                )
            )
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        addProfilePicture.bringToFront()
        addProfilePicture.setOnClickListener() {


            val NextIntent = Intent(this, ProfilePhotoCapture::class.java)
            startActivity(NextIntent)
        }

        cancelButton.setOnClickListener() {
            val NextIntent = Intent(this, MainActivity::class.java)
            startActivity(NextIntent)
        }

        updateRequiredfields()
    }

    private fun updateRequiredfields() {

        submitButton.setOnClickListener {

            /*        if (TextUtils.isEmpty(editFirstName2.text.toString())) {
                editCityField1.setError("First Name Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(editLastName2.text.toString())) {
                editCityField1.setError("Last Name Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(editCityField2.text.toString())) {
                editCityField1.setError("City Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(editStateField2.text.toString())) {
                editStateField2.setError("State Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(sportsAutocomplete.text.toString())) {
                sportsAutocomplete.setError("At Least One Sport is Required")
                return@setOnClickListener
            }
            println("Please complete all required fields before updating profile.")
        }*/

        }
    }
}





