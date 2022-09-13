package com.gunter.mobilenik.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gunter.mobilenik.R
import com.gunter.mobilenik.databinding.FragmentRecipeDetailBinding
import com.gunter.mobilenik.model.Recipe

private const val ARG_RECIPE = "recipe"

class RecipeDetailFragment : Fragment() {
    private var recipe: Recipe? = null

    private lateinit var binding: FragmentRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getSerializable(ARG_RECIPE) as? Recipe
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipe?.let {
            Glide.with(binding.root)
                .load(it.image)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.recipeImage)

            binding.recipeName.text = it.name
            binding.recipeHeadline.text = it.headline
            binding.recipeCalories.text = it.calories
            binding.recipeCarbos.text = it.carbos
            binding.recipeFats.text = it.fats
            binding.recipeProteins.text = it.proteins
            binding.recipeDifficulty.text = it.difficulty.toString()
            binding.recipeTime.text = it.time
            binding.recipeDescription.text = it.description
        }
    }

    companion object {
        fun newInstance(recipe: Recipe) = RecipeDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_RECIPE, recipe)
            }
        }
    }
}