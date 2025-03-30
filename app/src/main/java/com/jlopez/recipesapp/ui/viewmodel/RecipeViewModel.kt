package com.jlopez.recipesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.recipesapp.data.model.Recipe
import com.jlopez.recipesapp.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    fun loadRecipes() {
        viewModelScope.launch {
            recipeRepository.loadAndSaveRecipes() // load recipes from API and save in local DB
            _recipes.postValue(recipeRepository.getLocalRecipes()) // get recipes from DB
        }
    }
}