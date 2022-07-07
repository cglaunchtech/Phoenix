package com.example.sportssocial.ui.navigation

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.ui.view.LoginActivity
import com.example.sportssocial.ui.view.MainActivity
import com.example.sportssocial.ui.view.camera.ProfilePhotoCapture
import com.example.sportssocial.util.Constants
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.lang.Exception

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    lateinit var auth : FirebaseAuth
    var databaseReference : DatabaseReference? =null
    var database : FirebaseDatabase? = null

    //Page Elements
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        //Page Elements
        cancelButton = view.findViewById(R.id.cancelButton)
        submitButton = view.findViewById(R.id.submitButton)
        addProfilepic = view.findViewById(R.id.addProfilePicture)
        firstNameField =  view.findViewById(R.id.firstName2)
        lastNameField  =  view.findViewById(R.id.lastName2)
        usernameField =  view.findViewById(R.id.username2)
        emailField  =  view.findViewById(R.id.emailField2)
        passwordField =  view.findViewById(R.id.passwordField2)
        confirmPassword =  view.findViewById(R.id.confirmPasswordField2)
        cityField =  view.findViewById(R.id.cityField2)
        stateField =  view.findViewById(R.id.stateField2)
        birthdayField =  view.findViewById(R.id.birthdayField2)
        aboutMeField =  view.findViewById(R.id.aboutMeField2)
        sportsSelection =  view.findViewById(R.id.sportsAutocomplete)
        sportsSelectionTwo =  view.findViewById(R.id.sportsAutocompleteSecond)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")


        //TODO: SET SPINNER INFORMATION

        //Page Actions
        cancelButton.setOnClickListener {
            //TODO:
            Toast.makeText(requireContext(), "SignUp Cancelled", Toast.LENGTH_LONG).show()
            cancelButton.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

/*        submitButton.setOnClickListener {
            //TODO: CHECK FIELDS FOR APPROPRIATE INPUT
            //TODO: DISABLE BUTTON IF FORM EMPTY
            Toast.makeText(requireContext(), "Welcome to Sports Social!", Toast.LENGTH_LONG).show()
            submitButton.findNavController().navigate(R.id.action_loginFragment_to_containerFragment)
        }*/



        register()

        var profilePhoto: ImageView = view.findViewById(R.id.addProfilePicture)
        profilePhotostr= getActivity()?.getIntent()?.getStringExtra("profilePhoto")

        //decode base64 string
        if (profilePhotostr != null) {

            var bytes: ByteArray = Base64.decode(profilePhotostr, Base64.DEFAULT);
            // Initialize bitmap
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size);
            // set bitmap on imageView
            profilePhoto.setImageBitmap(bitmap);
        }
        cancelButton.setOnClickListener {
            Toast.makeText(requireContext(), "Sign Up Cancelled", Toast.LENGTH_LONG).show()
            cancelButton.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        addProfilepic.bringToFront()
        addProfilepic.setOnClickListener() {

            val NextIntent = Intent(requireContext(), ProfilePhotoCapture::class.java)
            startActivity(NextIntent)
        }
        return view

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
                    Toast.makeText(requireContext(), "Successfully Registered", Toast.LENGTH_LONG).show()
                    firestoreAthleteInit()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)

                    //view?.findNavController()?.navigate(R.id.action_signUpFragment_to_containerFragment)
                }

                .addOnFailureListener {
                    Log.d("AppDatabase","AAA else 1")
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage(it.message)
                    builder.setCancelable(true)
                    builder.setNegativeButton("OK", DialogInterface.OnClickListener
                    { dialog, which -> dialog.cancel() })
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.show()
                    Toast.makeText(requireContext(), "Registration Failed; Please Try Again", Toast.LENGTH_LONG).show()
                }
        }
    }
    private fun firestoreAthleteInit() = CoroutineScope(Dispatchers.IO).launch{
        try {
            Firebase.firestore.collection("users")
            Constants.FIRESTORE.add(
                Athlete(
                id = null,
                uid =  auth.uid,
                username = usernameField.text.toString(),
                profilePhoto = null,
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
                following = mutableListOf(),
            )
            ).await()

        }catch (e: Exception){
            Timber.e(e)
        }
    }

}

