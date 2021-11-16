package com.mohamed.overplay.di

import com.mohamed.overplay.data.ISessionCountLocalDatasource
import com.mohamed.overplay.data.SessionCountLocalDataSource
import com.mohamed.overplay.data.SessionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

	@Provides
	@Singleton
	fun provideSessionLocalDataSource(sessionDao: SessionDao): ISessionCountLocalDatasource {
		return SessionCountLocalDataSource(sessionDao)
	}
}