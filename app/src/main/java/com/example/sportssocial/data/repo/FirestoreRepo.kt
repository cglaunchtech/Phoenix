package com.example.sportssocial.data.repo

import android.net.Uri
import android.util.Log
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.util.Constants
import com.example.sportssocial.util.Constants.Companion.FIRESTORE
import com.google.firebase.firestore.ktx.toObject
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.*
import kotlin.Exception

class FirestoreRepo {

    lateinit var followingList : MutableList<Athlete>


    fun newProfile(athlete: Athlete) = CoroutineScope(Dispatchers.IO).launch{
        try{
            FIRESTORE.add(athlete).await()
        }catch(e: Exception){
            Timber.e(e)
        }
    }

    fun getAllProfiles(): Observable<List<Athlete>> {
        val userList = mutableListOf<Athlete>()
        runBlocking {
            try {
                val querySnapshot = FIRESTORE.get().await()

                for (document in querySnapshot.documents) {
                    val user = document.toObject<Athlete>()
                    userList.add(user!!)
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return Observable.just(userList)
    }

     fun getProfileByUsername(following: List<String>) : MutableList<Athlete> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = FIRESTORE
                    .whereArrayContainsAny("username", following)
                    .get()
                    .await()

                for (document in querySnapshot.documents) {
                    val user = document.toObject<Athlete>()
                    followingList.add(user!!)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return followingList
    }

    fun updateProfile(athlete : Athlete) =  CoroutineScope(Dispatchers.IO).launch {
        val personQuery = FIRESTORE
            .whereEqualTo("uid", athlete.uid)
            .get()
            .await()
        if(personQuery.documents.isNotEmpty()){
            for(document in personQuery) {
                try {
                    FIRESTORE.document(document.id).set(athlete)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }else{
            Timber.d("Search for user in firestore failed to find.")
        }
    }

    fun uploadToFirebase(uri : Uri?, athlete: Athlete) = CoroutineScope(Dispatchers.IO).launch{
        val filename = UUID.randomUUID().toString()
        val ref = Constants.STORAGE.getReference("images/$filename")
        try {

            if (uri != null) {
                ref.putFile(uri)
                    .addOnSuccessListener {
                        Timber.d("Firestore", "Successfully uploaded photo to firestore")
                        ref.downloadUrl.addOnCompleteListener {
                            athlete.profilePhoto = it.result.toString()
                            newProfile(athlete)
                        }
                    }
                    .addOnFailureListener {
                        Timber.e("Failed to upload image: $it")
                        newProfile(athlete)
                    }
            }else{
                newProfile(athlete)
            }

        } catch (e: java.lang.Exception) {
            Timber.e(e)
        }
    }

//    fun getAllImages(): MutableList<String>{
//        var imageUrls = mutableListOf<String>()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val querySnapshot = FIRESTORE
//                    .get()
//                    .await()
//
//                for (document in querySnapshot.documents) {
//
//                    val user = document.toObject<Athlete>()
//                    for (image in user.photoCollection) {
//                        imageUrls.add(image)
//                    }
//
//                }
//            } catch (e: Exception) {
//                Timber.e(e)
//            }
//        }
//        return imageUrls
//    }
}