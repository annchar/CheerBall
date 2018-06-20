package com.azaless.cheerball.view.model

import com.google.gson.annotations.SerializedName

data class Group(@SerializedName("A") val a: List<GroupDetail>?,
                 @SerializedName("B") val b: List<GroupDetail>?,
                 @SerializedName("C") val c: List<GroupDetail>?,
                 @SerializedName("D") val d: List<GroupDetail>?,
                 @SerializedName("E") val e: List<GroupDetail>?,
                 @SerializedName("F") val f: List<GroupDetail>?,
                 @SerializedName("G") val g: List<GroupDetail>?,
                 @SerializedName("H") val h: List<GroupDetail>?
)

data class GroupDetail(val group: String?,
                 val rank: Int?,
                 val team: String?,
                 val teamId: Int?,
                 val playedGames: Int?,
                 val crestURI: String?,
                 val points: Int?,
                 val goals: Int?,
                 val goalsAgainst: Int?,
                 val goalDifference: Int?
)