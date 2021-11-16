package com.mohamed.overplay.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SessionEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase: RoomDatabase() {
	abstract fun sessionDao(): SessionDao
}