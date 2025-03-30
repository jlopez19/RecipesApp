package com.jlopez.recipesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlopez.recipesapp.data.model.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(Convert::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: RecipeDatabase? = null


        operator fun invoke(context: Context) = instance ?: synchronized(this){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecipeDatabase::class.java,
                "recipe-db.db"
            ).build()
    }
}