package com.azaless.cheerball

import android.app.Application
import android.content.Context
import timber.log.Timber


class CheerBallApplication : Application() {

	operator fun get(context: Context): CheerBallApplication {
		return context.applicationContext as CheerBallApplication
	}

	override fun onCreate() {
		super.onCreate()
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
	}
}