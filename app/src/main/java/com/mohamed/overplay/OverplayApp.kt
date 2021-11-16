package com.mohamed.overplay

import android.app.Application
import com.mohamed.overplay.di.AppComponent
import com.mohamed.overplay.di.DaggerAppComponent
import com.mohamed.overplay.di.DatabaseModule

class OverplayApp: Application() {

	companion object {
		lateinit var appComponent: AppComponent
	}

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent
			.builder()
			.databaseModule(DatabaseModule(this))
			.build()
	}
}