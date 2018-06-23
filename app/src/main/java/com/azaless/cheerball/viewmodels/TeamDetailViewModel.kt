package com.azaless.cheerball.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v7.widget.RecyclerView
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.data.DefaultObserver
import com.azaless.cheerball.view.adapter.PlayerListAdapter
import com.azaless.cheerball.view.model.Player
import com.azaless.cheerball.view.model.Players
import com.azaless.cheerball.view.model.Team
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamDetailViewModel(private val cheerBallDataRepository: CheerBallDataRepository,
                          private val teamId: Int) : ViewModel() {
	private val flagURL = MutableLiveData<String>()
	private val team = MutableLiveData<Team>()
	private val players = MutableLiveData<List<Player>>()
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	val teamName = MutableLiveData<String>()
	val adapter = MutableLiveData<PlayerListAdapter>()
	val layoutManager = MutableLiveData<RecyclerView.LayoutManager>()

	init {
		compositeDisposable.clear()

		val teamDisposable = cheerBallDataRepository.getTeam(teamId)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeOn(Schedulers.io())
			.subscribeWith(TeamObserver())
		compositeDisposable.add(teamDisposable)

		val playersDisposable = cheerBallDataRepository.getPlayer(teamId)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeOn(Schedulers.io())
			.subscribeWith(PlayersObserver())
		compositeDisposable.add(playersDisposable)
	}

	fun getFlagURL(): LiveData<String> {
		return flagURL
	}

	fun getTeam(): LiveData<Team> {
		return team
	}

	fun getPlayers(): LiveData<List<Player>> {
		return players
	}

	fun disposable() {
		compositeDisposable.dispose()
	}

	private inner class TeamObserver : DefaultObserver<Team>() {
		override fun onNext(t: Team) {
			team.value = t
			flagURL.value = t.crestUrl
		}
	}

	private inner class PlayersObserver : DefaultObserver<Players>() {
		override fun onNext(t: Players) {
			players.value = t.players
		}
	}
}