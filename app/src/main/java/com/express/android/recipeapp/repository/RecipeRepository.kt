package com.express.android.recipeapp.repository

import com.express.android.recipeapp.api.ApiService
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getRecipe() = apiService.getRecipe()
}