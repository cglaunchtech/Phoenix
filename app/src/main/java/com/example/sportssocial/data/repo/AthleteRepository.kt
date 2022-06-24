package com.example.sportssocial.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sportssocial.data.db.AppDatabase
import com.example.sportssocial.data.model.AthleteDao
import com.example.sportssocial.data.model.Athletes


class AthleteRepository (context: Context) {
    var db: AthleteDao? = AppDatabase.getInstance(context)?.athleteDao()

    fun getAllAthletes(): LiveData<List<Athletes>>? {

        return db?.selectAthlete()
    }

    fun insertAthlete(athletes: Athletes) {

        db?.insertAthlete(athletes)
    }

    fun updateAthlete(athletes: Athletes) {
        db?.updateAthlete(athletes)
    }

    fun deleteAthlete(athletes: Athletes) {
        db?.deleteAthlete(athletes)
    }

    fun findAthletebyUsername(search: String): List<Athletes> {

        return db?.findAthletesbyUsername(search)!!
    }

    fun search(searchText: String): LiveData<List<Athletes>>? {
        return db?.search(searchText)

    }
}
