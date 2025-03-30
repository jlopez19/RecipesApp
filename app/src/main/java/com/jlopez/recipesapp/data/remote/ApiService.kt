package com.jlopez.recipesapp.data.remote

import com.jlopez.recipesapp.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes")
        suspend fun getRecipes(@Query("limit") limit: Int): RecipeResponse
}