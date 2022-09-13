package com.gunter.mobilenik.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gunter.mobilenik.R
import com.gunter.mobilenik.databinding.ActivityMainBinding
import com.gunter.mobilenik.model.Recipe

class MainActivity : AppCompatActivity(), OnRecipeClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, RecipesFragment.newInstance())
            .commit()
    }

    override fun onRecipeClick(recipe: Recipe) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.mainContainer, RecipeDetailFragment.newInstance(recipe))
            .addToBackStack(null)
            .commit()
    }
}