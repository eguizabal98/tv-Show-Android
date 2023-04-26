package com.eem.localdata.database

import com.eem.data.model.authentication.DataSessionId
import com.eem.localdata.util.SqlConverter.toData
import com.eem.sqldelight.SessionIdEntityQueries

class SessionIdDaoImpl(private val sessionIdEntityQueries: SessionIdEntityQueries) : SessionIdDao {

    override suspend fun getSessionId(): DataSessionId? =
        sessionIdEntityQueries.getSessionId().executeAsOneOrNull()?.toData()

    override suspend fun insertSessionId(sessionId: String) {
        sessionIdEntityQueries.insertSessionId(null, sessionId)
    }

    override suspend fun clearSessionId() {
        sessionIdEntityQueries.clearSessionId()
    }
}
