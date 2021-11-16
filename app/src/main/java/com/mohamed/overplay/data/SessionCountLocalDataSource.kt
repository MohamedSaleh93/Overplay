package com.mohamed.overplay.data

import javax.inject.Inject

class SessionCountLocalDataSource @Inject constructor(
	private val sessionDao: SessionDao): ISessionCountLocalDatasource {
	override suspend fun getSession(): SessionEntity? {
		return sessionDao.getSession()
	}

	override suspend fun addSession(sessionEntity: SessionEntity) {
		sessionDao.insertSession(sessionEntity)
	}
}