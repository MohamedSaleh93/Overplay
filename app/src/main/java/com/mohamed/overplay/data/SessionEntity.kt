package com.mohamed.overplay.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Session")
data class SessionEntity(
	@PrimaryKey
	val id: Int = 1,
	@ColumnInfo(name = "sessions_count")
	val sessionsCount: Int,
	@ColumnInfo(name = "last_session_time")
	val lastSessionTime: Date?
)
