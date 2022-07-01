package com.example.sportssocial.data.model.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "athletes")
data class Athlete(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "uid") var uid: String? = "",
    @ColumnInfo(name = "username") var username: String? = "",
    @ColumnInfo(name = "profilePhoto") var profilePhoto: String? = "",
    @ColumnInfo(name = "first") var first: String? = "",
    @ColumnInfo(name = "last") var last: String? = "",
    @ColumnInfo(name = "city") var city: String? = "",
    @ColumnInfo(name = "state") var state: String? = "",
    @ColumnInfo(name = "DOB") var dob: String? = "",
    @ColumnInfo(name = "aboutMe") var aboutMe: String? = "",
    @ColumnInfo(name = "sport1") var sport1: String? = "",
    @ColumnInfo(name = "sport2") var sport2: String? = "",
    @ColumnInfo(name = "photoCollection") var photoCollection: List<String?>? = listOf(),
    @ColumnInfo(name = "highlightVideos") var highlightVideos: List<String?>? = listOf(),
    @ColumnInfo(name = "following") var following: List<String?>? = listOf(),
)

