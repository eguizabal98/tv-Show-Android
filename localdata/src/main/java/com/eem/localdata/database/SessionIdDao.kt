package com.eem.localdata.database

import com.eem.data.model.authentication.DataSessionId

interface SessionIdDao {
    suspend fun getSessionId(): DataSessionId?
    suspend fun insertSessionId(sessionId: String)
    suspend fun clearSessionId()
}
