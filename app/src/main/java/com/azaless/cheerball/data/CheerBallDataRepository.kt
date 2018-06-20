package com.azaless.cheerball.data

import com.azaless.cheerball.view.model.LeagueGroup
import com.azaless.cheerball.view.model.Players
import com.azaless.cheerball.view.model.Team
import com.azaless.cheerball.view.model.Teams
import io.reactivex.Observable

class CheerBallDataRepository : CheerBallRepository {
	override fun getTeam(teamId: Int): Observable<Team> {
		return CheerBallApi.create().getTeam(teamId)
	}

	override fun getPlayer(teamId: Int): Observable<Players> {
		return CheerBallApi.create().getPlayers(teamId)
	}

	override fun getTeams(eventId: Int): Observable<Teams> {
		return CheerBallApi.create().getTeams(eventId)
	}

	override fun getLeagueGroup(eventId: Int): Observable<LeagueGroup> {
		return CheerBallApi.create().getLeagueGroup(eventId)
	}

	companion object {

		// For Singleton instantiation
		@Volatile private var instance: CheerBallDataRepository? = null

		fun getInstance() =
			instance ?: synchronized(this) {
				instance ?: CheerBallDataRepository().also { instance = it }
			}
	}
}