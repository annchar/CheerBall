package com.azaless.cheerball.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryModel(val flagUrl: String,
                        val flagName: String
) : Parcelable