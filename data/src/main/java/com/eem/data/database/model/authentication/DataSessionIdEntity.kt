package com.eem.data.database.model.authentication

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.authentication.SessionId

@Entity
data class DataSessionIdEntity(
    @PrimaryKey
    val sessionId: String
) : DomainMapper<SessionId> {

    override fun mapToDomainModel(): SessionId = SessionId(sessionId)
}
