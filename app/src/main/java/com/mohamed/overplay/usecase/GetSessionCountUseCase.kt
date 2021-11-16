package com.mohamed.overplay.usecase

import com.mohamed.overplay.data.ISessionCountRepository
import com.mohamed.overplay.data.SessionEntity
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetSessionCountUseCase @Inject constructor(
	private val sessionCountRepository: ISessionCountRepository
) {

	suspend fun getSessionCount(): Int {
		val sessionEntity = sessionCountRepository.getSession()

		if (sessionEntity != null) {
			val diffInMins = getDiffInMins(sessionEntity.lastSessionTime!!.time)
			return if (diffInMins > 12) {
				sessionCountRepository.addSession(
					SessionEntity(
						sessionsCount = sessionEntity.sessionsCount + 1,
						lastSessionTime = Date()
					)
				)
				sessionEntity.sessionsCount + 1
			} else {
				sessionEntity.sessionsCount
			}

		} else {
			sessionCountRepository.addSession(
				SessionEntity(
					sessionsCount = 1,
					lastSessionTime = Date()
				)
			)
			return 1
		}
	}

	private fun getDiffInMins(lastSessionTime: Long): Long {
		val currentDate = Date()
		return TimeUnit.MILLISECONDS.toMinutes(currentDate.time - lastSessionTime)
	}
}