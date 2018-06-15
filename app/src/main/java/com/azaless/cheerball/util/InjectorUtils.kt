package com.azaless.cheerball.util

import android.content.Context
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.view.ui.team.TeamViewModelFactory

object InjectorUtils {
	fun provideTeamViewModelFactory(
		context: Context,
		teamId: Int,
		teamName: String
	): TeamViewModelFactory {
		return TeamViewModelFactory(CheerBallDataRepository.getInstance(),teamId, teamName)
	}
}