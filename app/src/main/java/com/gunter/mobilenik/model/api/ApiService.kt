package com.gunter.mobilenik.model.api

import com.gunter.mobilenik.model.Recipe
import com.gunter.mobilenik.model.api.ApiConstants.ENDPOINT_RECIPES
import io.reactivex.Single
import retrofit2.http.GET

interface RecipeService {
    @GET(ENDPOINT_RECIPES)
    fun getAllRecipes(): Single<List<Recipe>>
}