package com.example.sportssocial.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AthleteDao {

    @Insert
    fun insertAthlete(athletes: Athletes)

    @Query("select * from athletes")
    fun selectAthlete(): LiveData<List<Athletes>>

    @Update
    fun updateAthlete(athletes: Athletes)

    @Delete
    fun deleteAthlete(athletes: Athletes)


    @Query("delete from athletes")
    fun deleteAll()

    @Query("select * from athletes where Id like :search")
    fun findAthletebyId(search: String): List<Athletes>


    @Query("select * from athletes where username like :search")
    fun findAthletesbyUsername(search: String): List<Athletes>

    @Query("select * from athletes where upper(username) like '%' || upper(:searchText) || '%' ")
    fun search(searchText : String): LiveData<List<Athletes>>
}
