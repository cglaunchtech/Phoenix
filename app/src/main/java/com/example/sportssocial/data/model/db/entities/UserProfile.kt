package com.example.sportssocial.data.model.db.entities

import com.example.sportssocial.data.model.db.entities.Athlete


data class UserProfile(
    var uid : String?,
    var user : Athlete,
    var mediaImage : MutableList<String>?,
    var mediaVideo : MutableList<String>?
)



