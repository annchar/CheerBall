package com.azaless.cheerball.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azaless.cheerball.data.CheerBallDataRepository
import com.azaless.cheerball.data.DefaultObserver
import com.azaless.cheerball.view.model.Teams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TeamsViewModel(private val cheerBallDataRepository: CheerBallDataRepository,
                     private val eventId: Int) : ViewModel() {

	private val teams = MutableLiveData<Teams>()

	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	init {
		compositeDisposable.clear()

		val teamsDisposable = cheerBallDataRepository.getTeams(eventId)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeOn(Schedulers.io())
			.subscribeWith(TeamsObserver())
		compositeDisposable.add(teamsDisposable)
	}

	fun getTeams(): LiveData<Teams> {
		return  teams
	}

	private inner class TeamsObserver : DefaultObserver<Teams>() {
		override fun onNext(t: Teams) {
			Timber.e("load list teams : ${t.toString()}")
			teams.value = t
		}
	}

}