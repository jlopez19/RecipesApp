package com.jlopez.recipesapp.di

import android.app.Application
import androidx.room.Room
import com.jlopez.recipesapp.data.remote.RetrofitInstance
import com.jlopez.recipesapp.data.local.RecipeDao
import com.jlopez.recipesapp.data.local.RecipeDatabase
import com.jlopez.recipesapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(app, RecipeDatabase::class.java, "recipe-db.bd").build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(db: RecipeDatabase): RecipeDao {
        return db.recipeDao()
    }

}