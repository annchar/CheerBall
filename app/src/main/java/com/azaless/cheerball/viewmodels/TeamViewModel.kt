package com.azaless.cheerball.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.data.DefaultObserver
import com.azaless.cheerball.view.model.Team
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TeamViewModel(private val cheerBallDataRepository: CheerBallDataRepository,
                    private val teamId: Int,
                    private val teamName: String) : ViewModel() {
	private val flagURL = MutableLiveData<String>()
	private val team = MutableLiveData<Team>()
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	init {
		val disposable = cheerBallDataRepository.getTeam(teamId)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeOn(Schedulers.io())
			.subscribeWith(TeamObserver())
		compositeDisposable.add(disposable)
	}
	fun getFlagURL(): LiveData<String> {
		return flagURL
	}

	fun getTeam(): LiveData<Team> {
		return team
	}

	inner class TeamObserver : DefaultObserver<Team>() {
		override fun onNext(t: Team) {
			Timber.e("Result : $t")
			team.value = t
			flagURL.value = t.crestUrl
		}
	}
}