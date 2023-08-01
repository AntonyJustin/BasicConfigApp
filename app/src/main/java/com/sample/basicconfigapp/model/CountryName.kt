package com.sample.basicconfigapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryName(
    val CommonName : String ="",
) : Parcelable

