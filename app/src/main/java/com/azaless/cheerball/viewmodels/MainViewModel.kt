package com.azaless.cheerball.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {
	private val timeSetup = MutableLiveData<Int>()
	fun initTime() {
		timeSetup.value = 5
	}

	fun getTimeSetup(): LiveData<Int> {
		return timeSetup
	}
}