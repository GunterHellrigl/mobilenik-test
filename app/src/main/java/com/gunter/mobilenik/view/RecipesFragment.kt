package com.gunter.mobilenik.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gunter.mobilenik.adapters.RecipeAdapter
import com.gunter.mobilenik.databinding.FragmentRecipesBinding
import com.gunter.mobilenik.model.Recipe
import com.gunter.mobilenik.viewModel.RecipesViewModel

class RecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var adapter: RecipeAdapter
    private lateinit var onRecipeClickListener: OnRecipeClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onRecipeClickListener = context as OnRecipeClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement onRecipeClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecipeAdapter {
            onRecipeClickListener.onRecipeClick(it)
        }
        binding.rvRecipes.adapter = adapter

        setupObservers()

        viewModel.onGetAllRecipes()
    }

    private fun setupObservers() {
        viewModel.showToastError.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.showLoadingIndicator.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            binding.progressBar.visibility = View.GONE
            adapter.addAll(recipes)
        }
    }

    companion object {
        fun newInstance() = RecipesFragment()
    }
}

interface OnRecipeClickListener {
    fun onRecipeClick(recipe: Recipe)
}