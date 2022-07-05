package com.example.sportssocial.data.model.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportssocial.data.model.db.entities.Athlete
import io.reactivex.rxjava3.core.Observable

@Dao
interface AthleteDao {

    @Insert
    fun insertAthlete(athletes: Athlete)

    @Query("select * from athletes")
    fun selectAthlete(): Observable<List<Athlete>>

    @Update
    fun updateAthlete(athletes: Athlete)

    @Delete
    fun deleteAthlete(athlete: Athlete)


    @Query("delete from athletes")
    fun deleteAll()

    @Query("select * from athletes where id like :search")
    fun findAthletebyId(search: String): List<Athlete>


    @Query("select * from athletes where username like :search")
    fun findAthletesbyUsername(search: String): List<Athlete>

    @Query("select * from athletes where upper(username) like '%' || upper(:searchText) || '%' ")
    fun search(searchText : String): Observable<List<Athlete>>
}
