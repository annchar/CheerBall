package com.azaless.cheerball.util

import android.content.Context
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.view.ui.allteam.TeamsViewModelFactory
import com.azaless.cheerball.view.ui.groupsball.GroupsBallViewModelFactory
import com.azaless.cheerball.view.ui.team.TeamDetailViewModelFactory

object InjectorUtils {
	fun provideTeamViewModelFactory(
			context: Context,
			teamId: Int,
			teamName: String): TeamDetailViewModelFactory {
		return TeamDetailViewModelFactory(CheerBallDataRepository.getInstance(),teamId, teamName)
	}

	fun provideTeamsViewModelFactory(
			context: Context,
			eventId: Int): TeamsViewModelFactory {
		return TeamsViewModelFactory(CheerBallDataRepository.getInstance(),eventId)
	}

	fun provideGroupsBallViewModelFactory(
		context: Context,
		eventId: Int): GroupsBallViewModelFactory {
		return GroupsBallViewModelFactory(CheerBallDataRepository.getInstance(),eventId)
	}
}