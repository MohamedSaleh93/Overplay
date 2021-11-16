package com.mohamed.overplay.data


interface ISessionCountRepository {
	suspend fun getSession(): SessionEntity?

	suspend fun addSession(sessionEntity: SessionEntity)
}