package com.eem.localdata.source

import com.eem.data.datasource.local.LocalAuthenticationSource
import com.eem.data.model.authentication.DataSessionId
import com.eem.localdata.database.SessionIdDao

class LocalAuthenticationSourceImpl(
    private val sessionIdDao: SessionIdDao
) : LocalAuthenticationSource {
    override suspend fun getSessionId(): DataSessionId? = sessionIdDao.getSessionId()

    override suspend fun insertSessionId(sessionId: String) {
        sessionIdDao.clearSessionId()
        sessionIdDao.insertSessionId(sessionId)
    }
}
