package com.example.sportssocial.data.model

import java.net.URI

data class UserProfile(
    var uid : String?,
    var firstName : String?,
    var lastName : String?,
    var city : String?,
    var state : String?,
    var DOB : String?,
    var aboutMe : String?,
    var sport : String?,
    var title : String?,
    var media : MutableList<URI>?
)


