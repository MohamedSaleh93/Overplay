package com.mohamed.overplay.di

import com.mohamed.overplay.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		DatabaseModule::class,
		DataSourceModule::class,
		RepositoryModule::class,
		ViewModelFactoryModule::class,
		UseCaseModule::class
	]
)
interface AppComponent {

	fun inject(mainActivity: MainActivity)
}