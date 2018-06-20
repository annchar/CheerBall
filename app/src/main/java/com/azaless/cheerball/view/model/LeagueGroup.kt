package com.azaless.cheerball.view.model

data class LeagueGroup(val leagueCaption: String?,
                       val matchday: Int?,
                       val standings: Map<String, List<GroupDetail>>?
)