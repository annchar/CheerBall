package com.azaless.cheerball.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TeamViewModel(/*plantRepository: PlantRepository,*/
                    private val teamId: String,
                    private val teamName: String) : ViewModel() {
	private val flagURL = MutableLiveData<String>()

	fun getFlagURL(): LiveData<String> {
		return flagURL
	}

//	fun getPlayers(): LiveData<List<PlayerModel>> {
//		// TODO: Create repo connect api repo.getPlayers
//	}
}