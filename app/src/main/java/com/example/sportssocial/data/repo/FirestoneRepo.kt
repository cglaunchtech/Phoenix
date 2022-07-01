package com.example.sportssocial.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.util.Constants.Companion.FIRESTORE
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import kotlin.Exception

class FirestoneRepo {
    var userList: MutableLiveData<List<Athlete>> //Observable
    lateinit var followingList : MutableList<Athlete>

    init{
        userList = MutableLiveData(getAllProfiles())
    }

    fun newProfile(athlete: Athlete) = CoroutineScope(Dispatchers.IO).launch{
        try{
            FIRESTORE.add(athlete)
        }catch(e: Exception){
            Timber.e(e)
        }
    }

    private fun getAllProfiles(): MutableList<Athlete> {
        val tempList = mutableListOf<Athlete>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = FIRESTORE.get().await()

                for (document in querySnapshot.documents) {
                    val user = document.toObject<Athlete>()
                    tempList.add(user!!)
                }
                userList.postValue(tempList)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return tempList
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
            .whereEqualTo("uid", "dbUid")
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