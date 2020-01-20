package com.azaless.cheerball.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
	private val timeSetup = MutableLiveData<Int>()
	fun initTime() {
		timeSetup.value = 5
	}

	fun getTimeSetup(): LiveData<Int> {
		return timeSetup
	}
}