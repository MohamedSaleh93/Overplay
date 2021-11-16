package com.mohamed.overplay.di

import android.app.Application
import androidx.room.Room
import com.mohamed.overplay.data.AppDatabase
import com.mohamed.overplay.data.SessionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(var application: Application) {

	@Singleton
	@Provides
	fun provideRoomDatabase(): AppDatabase {
		return Room.databaseBuilder(application,
			AppDatabase::class.java,
			"SessionsCounter-db")
			.build()
	}

	@Singleton
	@Provides
	fun provideSessionDao(appDatabase: AppDatabase): SessionDao {
		return appDatabase.sessionDao()
	}
}