package com.example.sportssocial.ui.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.view.camera.ProfilePhotoCapture
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.grpc.InternalChannelz.id
import kotlinx.android.synthetic.main.activity_edit_form.*
import kotlinx.android.synthetic.main.activity_edit_form.cityField
import kotlinx.android.synthetic.main.activity_edit_form.firstName1
import kotlinx.android.synthetic.main.activity_edit_form.lastName1
import kotlinx.android.synthetic.main.activity_edit_form.sportsAutocomplete
import kotlinx.android.synthetic.main.activity_edit_form.submitButton
import kotlinx.android.synthetic.main.signup_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception


class UpdateProfile : AppCompatActivity() {
    //val repo: AthleteRepository by lazy { AthleteRepository(this) }
    var databaseReference : DatabaseReference? =null
    var database : FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    lateinit var vm: MainViewModel
    lateinit var repo: FirestoreRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_form)


        var firstName1: TextInputEditText = findViewById(R.id.firstName1)
        var lastName1: TextInputEditText = findViewById(R.id.lastName1)
        var cityField1: TextInputEditText = findViewById(R.id.cityField1)
        var stateField1: TextInputEditText = findViewById(R.id.stateField1)
        var aboutMeField1: TextInputEditText = findViewById(R.id.aboutMeField1)
        var sportsAutocomplete: AutoCompleteTextView = findViewById(R.id.sportsAutocomplete)
        var sportsAutocompleteSecond: AutoCompleteTextView = findViewById(R.id.sportsAutocompleteSecond)
        var repo = FirestoreRepo()
        var submitButton: Button = findViewById(R.id.submitButton)
        var cancelButton: Button = findViewById(R.id.cancelButton)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        updateRequiredfields()

        var addProfilePicture: ShapeableImageView = findViewById(R.id.addProfilePicture)
        var profilePhotostr= intent.getStringExtra("profilePhoto")
        if (profilePhotostr != null) {

            var bytes: ByteArray = Base64.decode(profilePhotostr, Base64.DEFAULT);
            // Initialize bitmap
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size);
            // set bitmap on imageView
            addProfilePicture.setImageBitmap(bitmap);
        }

        firstName1.setText(intent.getStringExtra("first"))
        lastName1.setText(intent.getStringExtra("last"))
        cityField1.setText(intent.getStringExtra("city"))
        stateField1.setText(intent.getStringExtra("state"))
        aboutMeField1.setText(intent.getStringExtra("aboutMe"))
        sportsAutocomplete.setText(intent.getStringExtra("sport1"))
        sportsAutocompleteSecond.setText(intent.getStringExtra("sport2"))


        submitButton.setOnClickListener {
            vm.updateAthlete(
                Athlete(
                    id = null,
                    uid = auth.uid,
                    username = null,
                    profilePhoto = null,
                    first = firstName1.text.toString(),
                    last = lastName1.text.toString(),
                    city = cityField1.text.toString(),
                    state = stateField1.text.toString(),
                    dob = null,
                    aboutMe = aboutMeField1.text.toString(),
                    sport1 = sportsAutocomplete.text.toString(),
                    sport2 = sportsAutocompleteSecond.text.toString(),
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

            if (TextUtils.isEmpty(firstName1.text.toString())) {
                cityField.setError("First Name Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(lastName1.text.toString())) {
                cityField.setError("Last Name Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(cityField1.text.toString())) {
                cityField.setError("City Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(stateField1.text.toString())) {
                stateField1.setError("State Required")
                return@setOnClickListener

            } else if (TextUtils.isEmpty(sportsAutocomplete.text.toString())) {
                sportsAutocomplete.setError("At Least One Sport is Required")
                return@setOnClickListener
            }
            println("Please complete all required fields before updating profile.")
        }
        fun firestoreAthleteInit() = CoroutineScope(Dispatchers.IO).launch {
            try {
                //Firebase.firestore.collection("users").add(Athlete(....)).await()
                repo.updateProfile(
                    Athlete(
                        id = null,
                        uid = auth.uid,
                        username = usernameField.text.toString(),
                        profilePhoto = null,
                        first = firstName1.text.toString(),
                        last = lastName1.text.toString(),
                        city = cityField1.text.toString(),
                        state = stateField1.text.toString(),
                        dob = birthdayField.text.toString(),
                        aboutMe = aboutMeField1.text.toString(),
                        sport1 = sportsAutocomplete.text.toString(),
                        sport2 = null,
                        photoCollection = mutableListOf(),
                        highlightVideos = mutableListOf(),
                        following = mutableListOf()))

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}





