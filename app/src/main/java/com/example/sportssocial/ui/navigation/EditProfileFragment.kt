package com.example.sportssocial.ui.navigation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.view.MainActivity
import com.example.sportssocial.ui.view.camera.ProfilePhotoCapture
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    lateinit var repo: FirestoreRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        val vm: MainViewModel by viewModels()
        var repo = FirestoreRepo()
        var firstName2: TextInputEditText = view.findViewById(R.id.editFirstName2)
        var lastName2: TextInputEditText = view.findViewById(R.id.editLastName2)
        var cityField2: TextInputEditText = view.findViewById(R.id.editCityField2)
        var stateField2: TextInputEditText = view.findViewById(R.id.editStateField2)
        var aboutMeField2: TextInputEditText = view.findViewById(R.id.editAboutMeField2)
        var sportsAutocomplete: AutoCompleteTextView = view.findViewById(R.id.editSportsSelection1)
        var sportsAutocompleteSecond: AutoCompleteTextView = view.findViewById(R.id.editSportsSelection2)
        var submitButton: Button = view.findViewById(R.id.editSubmitButton)
        var cancelButton: Button = view.findViewById(R.id.editCancelButton)


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        updateRequiredfields(view)

        var addProfilePicture: ShapeableImageView = view.findViewById(R.id.editAddProfilePicture)
        /* var profilePhotostr = intent.getStringExtra("profilePhoto")
        if (profilePhotostr != null) {

            var bytes: ByteArray = Base64.decode(profilePhotostr, Base64.DEFAULT);
            // Initialize bitmap
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size);
            // set bitmap on imageView
            addProfilePicture.setImageBitmap(bitmap);
        }

        editFirstName2.setText(intent.getStringExtra("first"))
        lastName.setText(intent.getStringExtra("last"))
        cityField1.setText(intent.getStringExtra("city"))
        stateField2.setText(intent.getStringExtra("state"))
        aboutMeField.setText(intent.getStringExtra("aboutMe"))
        sportsAutocomplete.setText(intent.getStringExtra("sport1"))
        sportsAutocompleteSecond.setText(intent.getStringExtra("sport2"))
*/

        submitButton.setOnClickListener {
            vm.updateAthlete(
                Athlete(
                    id = null,
                    uid = auth.uid,
                    username = null,
                    profilePhoto = null,
                    first = editFirstName2.text.toString(),
                    last = lastName2.text.toString(),
                    city = cityField2.text.toString(),
                    state = stateField2.text.toString(),
                    dob = null,
                    aboutMe = editAboutMeField2.text.toString(),
                    sport1 = sportsAutocomplete.text.toString(),
                    sport2 = sportsAutocompleteSecond.text.toString(),
                    photoCollection = mutableListOf(),
                    highlightVideos = mutableListOf(),
                    following = mutableListOf()
                )
            )
            replaceFragment(ProfileFragment())
        }

        addProfilePicture.bringToFront()
        addProfilePicture.setOnClickListener() {


            val NextIntent = Intent(requireContext(), ProfilePhotoCapture::class.java)
            startActivity(NextIntent)
        }

        cancelButton.setOnClickListener() {
            Toast.makeText(requireContext(), "Edit Cancelled", Toast.LENGTH_LONG).show()
            replaceFragment(ProfileFragment())
        }

        updateRequiredfields(view)
        return view
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = parentFragmentManager
        transaction.beginTransaction().replace(
            R.id.fragmentContainer,
            fragment
        ).commit()
    }

    private fun updateRequiredfields(view : View) {

        view.editSubmitButton.setOnClickListener {

            if (TextUtils.isEmpty(editFirstName2.text.toString())) {
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

/*            } else if (TextUtils.isEmpty(sportsAutocomplete.text.toString())) {
                sportsAutocomplete.setError("At Least One Sport is Required")
                return@setOnClickListener
            }*/
                println("Please complete all required fields before updating profile.")
            }
        }
    }
}