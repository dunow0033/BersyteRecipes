package com.express.android.recipeapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val href: String,
    val ingredients: String,
    val thumbnail: String,
    val title: String
):Parcelable
