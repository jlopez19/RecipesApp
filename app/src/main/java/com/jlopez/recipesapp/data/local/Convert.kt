package com.jlopez.recipesapp.data.local

import androidx.room.TypeConverter

class Convert {
    @TypeConverter
    fun fromList(data: List<String>?): String? {
        return data?.joinToString(",")
    }

    @TypeConverter
    fun toList(dataString: String?): List<String>? {
        return dataString?.split(",")?.map { it.trim() }
    }
}