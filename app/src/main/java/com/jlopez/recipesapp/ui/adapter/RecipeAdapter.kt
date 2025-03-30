package com.jlopez.recipesapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jlopez.recipesapp.R
import com.jlopez.recipesapp.data.model.Recipe

class RecipeAdapter (private val onRecipeClick: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem == newItem
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.bind(recipe)

    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTv: TextView = itemView.findViewById(R.id.title)
        private val ratingTV: TextView = itemView.findViewById(R.id.rating)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(recipe: Recipe) {
            titleTv.text = recipe.name
            ratingTV.text = recipe.rating.toString()
            Glide.with(itemView)
                .load(recipe.image)
                .apply(RequestOptions.circleCropTransform())
                .into(image)

            itemView.setOnClickListener { onRecipeClick(recipe) }
        }

    }


}