package com.example.sportssocial.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sportssocial.data.model.AthleteDao
import com.example.sportssocial.data.model.Athletes


@Database(entities = [Athletes::class], version = 1, exportSchema = false)

abstract class AppDatabase: RoomDatabase() {

    abstract fun athleteDao(): AthleteDao


    companion object {

        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {

                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "athletes.db"
                    )
                        .allowMainThreadQueries().build()
                }

            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


