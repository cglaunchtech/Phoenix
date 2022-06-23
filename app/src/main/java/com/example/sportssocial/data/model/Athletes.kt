package com.example.sportssociallayouts.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "athletes")
data class Athletes      (@PrimaryKey(autoGenerate = true) var Id: Int?,
                          @ColumnInfo(name = "username") var username: String?,
                          @ColumnInfo(name = "password") var password: String?,
                          @ColumnInfo(name = "city") var city: String?,
                          @ColumnInfo(name = "state") var state: String?,
                          @ColumnInfo(name = "DOB") var DOB: Date?,
                          @ColumnInfo(name = "aboutMe") var aboutMe: String,
                          @ColumnInfo(name = "sport") var sport: String,
                          @ColumnInfo(name = "title") var title: String,)

