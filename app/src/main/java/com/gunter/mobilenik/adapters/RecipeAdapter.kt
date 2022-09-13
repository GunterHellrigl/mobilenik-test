package com.gunter.mobilenik.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunter.mobilenik.R
import com.gunter.mobilenik.databinding.ItemRecipeBinding
import com.gunter.mobilenik.model.Recipe

class RecipeAdapter(
    private val listener: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val recipes: MutableList<Recipe> = mutableListOf()

    fun addAll(recipes: List<Recipe>) =
        this.recipes.addAll(recipes).also { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(recipes[position])

    override fun getItemCount(): Int = recipes.size
}

class RecipeViewHolder(
    private val binding: ItemRecipeBinding,
    private val listener: (Recipe) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: Recipe) {
        Glide.with(binding.root)
            .load(recipe.image)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(binding.itemRecipeImage)
        binding.itemRecipeTitle.text = recipe.name
        binding.itemRecipeDescription.text = recipe.description

        itemView.setOnClickListener { listener(recipe) }
    }
}