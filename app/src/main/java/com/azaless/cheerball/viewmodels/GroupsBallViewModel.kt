package com.azaless.cheerball.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.data.DefaultObserver
import com.azaless.cheerball.view.model.LeagueGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GroupsBallViewModel(private val cheerBallDataRepository: CheerBallDataRepository,
                          private val eventId: Int) : ViewModel() {

	private val leagueGroup = MutableLiveData<LeagueGroup>()

	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	init {
		compositeDisposable.clear()

		val leagueGroupDisposable = cheerBallDataRepository.getLeagueGroup(eventId)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeOn(Schedulers.io())
			.subscribeWith(LeagueGroupObserver())
		compositeDisposable.add(leagueGroupDisposable)
	}

	fun getLeagueGroup(): LiveData<LeagueGroup> {
		return  leagueGroup
	}

	private inner class LeagueGroupObserver : DefaultObserver<LeagueGroup>() {
		override fun onNext(t: LeagueGroup) {
			leagueGroup.value = t
		}
	}

}