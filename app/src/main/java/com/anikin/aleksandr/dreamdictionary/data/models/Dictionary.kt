package com.anikin.aleksandr.dreamdictionary.data.models

import com.google.gson.annotations.SerializedName

data class Dictionary(
    @field:SerializedName("keyword")
    val keyword: String?,
    @field:SerializedName("description")
    val description: String?
)