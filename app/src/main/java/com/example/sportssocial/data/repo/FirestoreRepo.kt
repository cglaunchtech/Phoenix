package com.example.sportssocial.data.repo

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.AppDatabase
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

class FirestoreRepo() {

    lateinit var followingList : MutableList<Athlete>


    fun newProfile(athlete: Athlete) = CoroutineScope(Dispatchers.IO).launch{
        try{
            FIRESTORE.add(athlete).await()
        }catch(e: Exception){
            Timber.e(e)
        }
    }

    fun getProfileByUid(uid: String): Observable<Athlete>{
        var user : Athlete? = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = FIRESTORE
                    .whereEqualTo("uid",uid)
                    .get()
                    .await()

                for (document in querySnapshot.documents) {
                   user = document.toObject<Athlete>()
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return Observable.just(user!!)
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
                    .whereIn("username", following)
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

    fun updateProfile(uid : String, athlete : Athlete) =  CoroutineScope(Dispatchers.IO).launch {
        val personQuery = FIRESTORE
            .whereEqualTo("uid", uid)
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

    fun getAllImages(): MutableList<String>{
        var imageUrls = mutableListOf<String>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = FIRESTORE
                    .get()
                    .await()

                for (document in querySnapshot.documents) {
                    val user = document.toObject<Athlete>()
                    if (user != null) user.photoCollection!!.forEach { image ->
                        if (image != null) {
                            imageUrls.add(image)
                        }
                    }

                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return imageUrls
    }

    fun uploadPhoto(uri : Uri?, athlete: Athlete) {
        val filename = UUID.randomUUID().toString()
        val ref = Constants.STORAGE.getReference("images/$filename")
        try {

            if (uri != null) {
                ref.putFile(uri)
                    .addOnSuccessListener {
                        Timber.d("Firestore", "Successfully uploaded photo to firestore")
                        ref.downloadUrl.addOnCompleteListener {
                            athlete.uid?.let { uid ->
                                addImageToCollection(it.result.toString() , uid)
                            }
                        }
                    }
                    .addOnFailureListener {
                        Timber.e("Failed to upload image: $it")
                    }
            }
        } catch (e: java.lang.Exception) {
            Timber.e(e)
        }
    }




    fun addImageToCollection(url : String, uid: String){
        var images = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).launch {
            val personQuery = FIRESTORE
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (document in personQuery.documents) {
                val user = document.toObject<Athlete>()
                if (user != null) user.photoCollection!!.forEach { image ->
                    if (image != null) {
                        images.add(image)
                    }
                    images.add(url)
                }
                user?.photoCollection = images
                if (user != null) updateProfile(uid, user)
            }
        }

    }
    fun removeImageFromCollection(url : String, uid: String){
        var images = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).launch {
            val personQuery = FIRESTORE
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (document in personQuery.documents) {
                val user = document.toObject<Athlete>()
                if (user != null) user.photoCollection!!.forEach { image ->
                    if (image != null) {
                        images.add(image)
                    }
                }
                images.remove(url)
                user?.photoCollection = images
                if (user != null) updateProfile(uid, user)
            }
        }

    }

    fun usersBySport(sport: String): Observable<List<Athlete>>{
        val searchList = mutableListOf<Athlete>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = FIRESTORE
                    .whereEqualTo("sport1", sport)
                    .whereEqualTo("sport2" , sport)
                    .get()
                    .await()

                for (document in querySnapshot.documents) {
                    val user = document.toObject<Athlete>()
                    searchList.add(user!!)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return Observable.just(searchList)
    }
}