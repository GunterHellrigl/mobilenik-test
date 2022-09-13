package com.gunter.mobilenik.viewModel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunter.mobilenik.model.Recipe
import com.gunter.mobilenik.model.api.ApiConstants
import com.gunter.mobilenik.model.api.ApiConstants.BASE_API_URL
import com.gunter.mobilenik.model.api.RecipeRequest
import com.gunter.mobilenik.model.api.RecipeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RecipesViewModel: ViewModel() {

    private lateinit var recipeRequest: RecipeRequest

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val _showToastError = MutableLiveData<String>()
    val showToastError: LiveData<String> get() = _showToastError

    private val _showLoadingIndicator = MutableLiveData<Boolean>()
    val showLoadingIndicator: LiveData<Boolean> get() = _showLoadingIndicator


    @SuppressLint("CheckResult")
    fun onGetAllRecipes() {
        recipeRequest = RecipeRequest(BASE_API_URL)

        recipeRequest
            .getService<RecipeService>()
            .getAllRecipes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _showLoadingIndicator.value = true
            }
            .subscribe({ recipes ->
                _recipes.value = recipes
                _showLoadingIndicator.value = false
            }, { error ->
                _showToastError.value = error.message
                _showLoadingIndicator.value = false
            })

    }
}