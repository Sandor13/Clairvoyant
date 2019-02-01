package com.anikin.aleksandr.dreamdictionary.data.models

import com.google.common.collect.ImmutableList
import com.google.gson.annotations.SerializedName

data class DictionaryJson(
    @field:SerializedName("dictionary")
    val dictionary: ImmutableList<Dictionary>?
)