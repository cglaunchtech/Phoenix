package com.example.sportssocial.util

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Constants {
    companion object {
        //TODO: constants go here
        val FIRESTORE = Firebase.firestore.collection("users")
    }
}