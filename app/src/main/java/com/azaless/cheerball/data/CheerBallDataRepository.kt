package com.azaless.cheerball.data

import com.azaless.cheerball.view.model.Team
import io.reactivex.Observable

class CheerBallDataRepository : CheerBallRepository {
	override fun getTeam(teamId: Int): Observable<Team> {
		return CheerBallApi.create().getTeam(teamId)
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