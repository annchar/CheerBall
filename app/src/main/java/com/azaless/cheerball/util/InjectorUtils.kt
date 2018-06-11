package com.azaless.cheerball.util

import android.content.Context
import com.azaless.cheerball.view.ui.team.TeamViewModelFactory

object InjectorUtils {
	// TODO: Set context for call repository call API
	fun provideTeamViewModelFactory(
		context: Context,
		teamId: String,
		teamName: String
	): TeamViewModelFactory {
		return TeamViewModelFactory(teamId, teamName)
	}
}