package com.example.sportssocial.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sportssocial.R
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileFragment : Fragment() {

    var databaseReference : DatabaseReference? = null
    var database : DatabaseReference? = null
    lateinit var auth: FirebaseAuth
    lateinit var firstNameField : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        val vm : MainViewModel by viewModels()


        //getUser(auth)


        return view
    }

    private fun getUser(user : FirebaseAuth) {

        database = FirebaseDatabase.getInstance()?.getReference("users")

        CoroutineScope(Dispatchers.IO).launch {
            if (auth.currentUser != null) {
                database?.child(auth.currentUser.toString())?.get()?.addOnSuccessListener {
                    if (it.exists()) {
                        val firstName = it.child("first")
                        val lastName = it.child("last")
                        val aboutMe = it.child("aboutMe")

                        Log.d("First Name", firstName.toString() )
                        Log.d("Last Name", lastName.toString() )
                        Log.d("About Me", aboutMe.toString() )

                    }
                }

            }
        }
    }

}