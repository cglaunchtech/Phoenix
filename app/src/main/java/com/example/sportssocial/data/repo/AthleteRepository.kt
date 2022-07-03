package com.example.sportssocial.data.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.db.AppDatabase
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.NewsArticle
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AthleteRepository (context: Context) {
    var db: AthleteDao? = AppDatabase.getInstance(context)?.athleteDao()

    fun getAllAthletes(): Observable<List<Athlete>>? {

        return db?.selectAthlete()
    }

    fun insertAthlete(athletes: Athlete) {

        db?.insertAthlete(athletes)
    }

    fun updateAthlete(athletes: Athlete) {
        db?.updateAthlete(athletes)
    }

    fun deleteAthlete(athlete: Athlete) {
        db?.deleteAthlete(athlete)
    }

    fun findAthletebyUsername(search: String): List<Athlete> {

        return db?.findAthletesbyUsername(search)!!
    }

    fun searchAthletes(searchText: String): Observable<List<Athlete>>? {
        return db?.search(searchText)
    }
}




