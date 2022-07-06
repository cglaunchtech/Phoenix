package com.example.sportssocial.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class Constants {
    companion object {
        //TODO: constants go here
        var STORAGE = FirebaseStorage.getInstance()
        val FIRESTORE = Firebase.firestore.collection("users")
        val AUTH = FirebaseAuth.getInstance()
    }
}