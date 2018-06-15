package com.azaless.cheerball.data

import com.azaless.cheerball.view.model.Team
import io.reactivex.Observable

interface CheerBallRepository {
	fun getTeam(teamId: Int): Observable<Team>
}