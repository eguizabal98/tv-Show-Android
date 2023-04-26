package com.eem.data.model.authentication

import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.base.DomainMapper

data class DataSessionId(
    val sessionId: String
) : DomainMapper<SessionId>() {

    override fun mapToDomainModel(): SessionId = SessionId(sessionId)
}
