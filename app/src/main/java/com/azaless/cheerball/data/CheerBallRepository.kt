package com.azaless.cheerball.data

import com.azaless.cheerball.view.model.Players
import com.azaless.cheerball.view.model.Team
import com.azaless.cheerball.view.model.Teams
import io.reactivex.Observable

interface CheerBallRepository {
	fun getTeam(teamId: Int): Observable<Team>

	fun getPlayer(teamId: Int): Observable<Players>

	fun getTeams(eventId: Int): Observable<Teams>
}