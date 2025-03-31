package com.jlopez.recipesapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jlopez.recipesapp.R
import com.jlopez.recipesapp.data.model.Recipe
import com.jlopez.recipesapp.databinding.ActivityRecipeBinding
import com.jlopez.recipesapp.ui.adapter.RecipeAdapter
import com.jlopez.recipesapp.ui.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupObservers()

    }
    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter { recipe -> showIngredientsDialog(recipe) }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeAdapter
    }

    private fun setupObservers() {
        recipeViewModel.loadRecipes()
        recipeViewModel.recipes.observe(this, Observer {recipes->
            recipeAdapter.differ.submitList(recipes)
        })
    }

    private fun showIngredientsDialog(recipe: Recipe) {
        AlertDialog.Builder(this)
            .setTitle(recipe.name)
            .setMessage(getString(R.string.ingredientes) + recipe.ingredients.toString().replace("[", "").replace("]", ""))
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}