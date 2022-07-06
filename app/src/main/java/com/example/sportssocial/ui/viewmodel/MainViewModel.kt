package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : AthleteRepository,
    private val app : Application
    ) : AndroidViewModel(app) {

    val allAthletes : Observable<List<Athlete>>?

    init {
        allAthletes = repo.getAllAthletes()
    }

    fun getAllAthletes() = viewModelScope.launch {

        repo.getAllAthletes()
    }

    fun insertAthlete(athletes: Athlete) = viewModelScope.launch{

        repo.insertAthlete(athletes)
    }

    fun updateAthlete(athlete: Athlete) = viewModelScope.launch {
        repo.updateAthlete(athlete)
    }

    fun deleteAthlete(athlete: Athlete) = viewModelScope.launch{
        repo.deleteAthlete(athlete)

        fun findAthletebyUsername(search: String): List<Athlete> {

            return repo.findAthletebyUsername(search)
        }
    }
}