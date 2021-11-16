package com.mohamed.overplay.di

import com.mohamed.overplay.usecase.GetSessionCountUseCase
import com.mohamed.overplay.view.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

	@Provides
	@Singleton
	fun provideMainViewModeFactory(
		getSessionCountUseCase: GetSessionCountUseCase
	): MainViewModelFactory {
		return MainViewModelFactory(getSessionCountUseCase)
	}
}