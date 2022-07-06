package com.example.sportssocial.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sportssocial.R
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileFragment : Fragment() {

    var databaseReference : DatabaseReference? = null
    var database = FirestoreRepo()
    lateinit var auth: FirebaseAuth
    lateinit var firstNameField : View
    lateinit var editButton : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        val vm : MainViewModel by viewModels()


        editButton = view.findViewById(R.id.editButton)
        editButton.setOnClickListener {
            replaceFragment(EditProfileFragment())
        }

        getUser()

        return view
    }

    private fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            if (auth.currentUser != null) {
                var user = database.getProfileByUid(auth.uid!!)

                user.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onNext = {
                            val firstName = it.first
                            val lastName = it.last
                            val aboutMe = it.aboutMe
                            val profilePhoto = it.profilePhoto
                            val city = it.city
                            val state = it.state
                            val username =it.username
                            val dob = it.dob
                            val photoCollection = it.photoCollection
                        Log.d("First Name", firstName.toString() )
                        Log.d("Last Name", lastName.toString() )
                        Log.d("About Me", aboutMe.toString() )
                                 })

            }
        }
    }

    private fun replaceFragment(fragment : Fragment) {

        val transaction = parentFragmentManager
        transaction.beginTransaction().replace(
            R.id.fragmentContainer,
            fragment
        ).commit()
    }

}