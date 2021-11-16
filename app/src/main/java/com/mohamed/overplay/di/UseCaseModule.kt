package com.mohamed.overplay.di

import com.mohamed.overplay.data.ISessionCountRepository
import com.mohamed.overplay.usecase.GetSessionCountUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

	@Provides
	@Singleton
	fun provideGetSessionCountUseCase(
		sessionCountRepository: ISessionCountRepository
	): GetSessionCountUseCase {
		return GetSessionCountUseCase(sessionCountRepository)
	}
}