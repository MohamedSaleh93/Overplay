package com.mohamed.overplay.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface SessionDao {

	@Insert(onConflict = REPLACE)
	suspend fun insertSession(session: SessionEntity)

	@Query("SELECT * FROM Session")
	suspend fun getSession(): SessionEntity?
}