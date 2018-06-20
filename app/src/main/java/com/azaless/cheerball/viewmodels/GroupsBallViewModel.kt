package com.azaless.cheerball.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.data.DefaultObserver
import com.azaless.cheerball.view.model.LeagueGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

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

	fun getLeagueGroup(): LiveData<LeagueGroup>{
		return  leagueGroup
	}

	private inner class LeagueGroupObserver : DefaultObserver<LeagueGroup>() {
		override fun onNext(t: LeagueGroup) {
			leagueGroup.value = t
			Timber.e("leagueGroup : ${t}")
		}
	}

}