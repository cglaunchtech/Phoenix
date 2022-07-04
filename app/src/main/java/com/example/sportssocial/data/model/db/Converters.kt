package com.example.sportssocial.data.model.db

import androidx.room.TypeConverter
import com.example.sportssocial.data.api.pojo.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class Converters {

    @TypeConverter
    @FromJson
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    @ToJson
    fun toSource(name: String): Source {
        return Source(name, name)
    }

    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}


