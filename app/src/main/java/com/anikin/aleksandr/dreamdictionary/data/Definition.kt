package com.anikin.aleksandr.dreamdictionary.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Definition(val keyword: String, val description: String) : Parcelable