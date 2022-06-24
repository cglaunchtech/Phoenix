package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sportssocial.data.model.Athletes
import com.example.sportssocial.data.repo.AthleteRepository

import kotlinx.coroutines.launch

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val repo: AthleteRepository
    val allAthletes : LiveData<List<Athletes>>?

    init {
        repo = AthleteRepository(app)
        allAthletes = repo.getAllAthletes()
    }

    fun getAllAthletes() = viewModelScope.launch {

        repo.getAllAthletes()
    }

    fun insertAthlete(athletes: Athletes) = viewModelScope.launch{

        repo.insertAthlete(athletes)
    }

    fun updateAthlete(athletes: Athletes) = viewModelScope.launch {
        repo.updateAthlete(athletes)
    }

    fun deleteAthlete(athletes: Athletes) = viewModelScope.launch{
        repo.deleteAthlete(athletes)

        fun findAthletebyUsername(search: String): List<Athletes> {

            return repo.findAthletebyUsername(search)
        }
    }
}