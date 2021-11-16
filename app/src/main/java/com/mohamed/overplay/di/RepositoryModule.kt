package com.mohamed.overplay.di

import com.mohamed.overplay.data.ISessionCountLocalDatasource
import com.mohamed.overplay.data.ISessionCountRepository
import com.mohamed.overplay.data.SessionCountRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

	@Provides
	@Singleton
	fun provideSessionRepository(sessionLocalDataSource: ISessionCountLocalDatasource): ISessionCountRepository {
		return SessionCountRepository(sessionLocalDataSource)
	}
}