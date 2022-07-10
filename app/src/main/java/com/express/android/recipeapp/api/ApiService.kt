package com.express.android.recipeapp.api

import com.express.android.recipeapp.models.RecipeResponse
import com.express.android.recipeapp.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getRecipe():Response<RecipeResponse>
}