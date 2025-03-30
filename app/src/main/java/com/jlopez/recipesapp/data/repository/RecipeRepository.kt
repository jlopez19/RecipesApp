package com.jlopez.recipesapp.data.repository

import com.jlopez.recipesapp.data.local.RecipeDao
import com.jlopez.recipesapp.data.model.Recipe
import com.jlopez.recipesapp.data.remote.ApiService
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val remoteDataSource: ApiService, private val localDataSource: RecipeDao) {

    suspend fun loadAndSaveRecipes() {
        val response = remoteDataSource.getRecipes(30)
        localDataSource.insertRecipe(response.recipes)
    }

    suspend fun getLocalRecipes(): List<Recipe> {
        return localDataSource.getAllRecipes()
    }
}