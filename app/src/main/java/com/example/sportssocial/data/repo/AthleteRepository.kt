package com.example.sportssocial.data.repo

import android.content.Context
import com.example.sportssocial.data.model.db.AppDatabase
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import io.reactivex.rxjava3.core.Observable

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




