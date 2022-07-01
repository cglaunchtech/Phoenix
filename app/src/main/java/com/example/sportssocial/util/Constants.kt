package com.example.sportssocial.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Constants {
    companion object {
        //TODO: constants go here
        val FIRESTORE = Firebase.firestore.collection("users")
        val AUTH = FirebaseAuth.getInstance()
    }
}