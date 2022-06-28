package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sportssocial.data.model.db.entities.UserProfile
import com.example.sportssocial.data.repo.SportsRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserProfileViewModel(app: Application): AndroidViewModel(app) {

    private val repo: SportsRepository
    var allProfiles : List<UserProfile>

    init {
        repo = SportsRepository(app)
        runBlocking {
            allProfiles = repo.fGetUsers()
        }

    }

    fun getAllProfiles() = viewModelScope.launch {
        repo.fGetUsers()
    }

    fun insertProfiles(user: UserProfile) = viewModelScope.launch{
        repo.fNewProfile(user)
    }

    fun updateProfiles(user: UserProfile) = viewModelScope.launch {
        repo.fUpdateProfile(user)
    }
/*
    fun deleteProfiles(athlete: Athlete) = viewModelScope.launch{
        repo.deleteAthlete(athlete)

        fun findProfilesbyUsername(search: String): List<Athlete> {

            return repo.findAthletebyUsername(search)
        }
    }*/
}