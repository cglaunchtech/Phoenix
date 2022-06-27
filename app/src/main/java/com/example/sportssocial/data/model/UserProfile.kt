package com.example.sportssocial.data.model

import java.net.URI

data class UserProfile(
    var uid : String?,
    var user : Person,
    var aboutMe : String?,
    var sport : String?,
    var mediaImage : MutableList<String>?,
    var mediaVideo : MutableList<String>?
)

data class Person(
    var firstName : String?,
    var lastName : String?,
    var city : String?,
    var state : String?,
    var DOB : String?
)


