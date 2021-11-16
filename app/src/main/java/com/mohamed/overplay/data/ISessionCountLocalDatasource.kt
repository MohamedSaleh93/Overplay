package com.mohamed.overplay.data


interface ISessionCountLocalDatasource {

	suspend fun getSession(): SessionEntity?

	suspend fun addSession(sessionEntity: SessionEntity)
}