package com.azaless.cheerball.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel(val name: String,
                       val position: String,
                       val jerseyNumber: String,
                       val age: String,
                       val dateOfBirth: String
) : Parcelable