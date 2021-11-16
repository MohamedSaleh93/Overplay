package com.mohamed.overplay.data

import javax.inject.Inject

class SessionCountRepository @Inject constructor(
	private val sessionLocalDatasource: ISessionCountLocalDatasource
) : ISessionCountRepository {
	override suspend fun getSession(): SessionEntity? {
		return sessionLocalDatasource.getSession()
	}

	override suspend fun addSession(sessionEntity: SessionEntity) {
		return sessionLocalDatasource.addSession(sessionEntity)
	}
}