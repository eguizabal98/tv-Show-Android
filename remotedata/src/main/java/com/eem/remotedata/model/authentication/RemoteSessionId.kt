package com.eem.remotedata.model.authentication

import com.eem.data.model.authentication.DataSessionId
import com.eem.data.model.base.DataMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSessionId(
    @SerialName("success")
    val success: Boolean,
    @SerialName("session_id")
    val sessionId: String
) : DataMapper<DataSessionId>() {

    override fun mapToDataModel(): DataSessionId = DataSessionId(success, sessionId)
}
