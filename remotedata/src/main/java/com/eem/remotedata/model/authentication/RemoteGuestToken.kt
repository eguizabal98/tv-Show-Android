package com.eem.remotedata.model.authentication

import com.eem.data.model.authentication.DataGuestToken
import com.eem.data.model.base.DataMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGuestToken(

    @SerialName("success")
    val success: Boolean,
    @SerialName("guest_session_id")
    val guestSessionId: String,
    @SerialName("expires_at")
    val expiresAt: String
) : DataMapper<DataGuestToken>() {

    override fun mapToDataModel(): DataGuestToken =
        DataGuestToken(success, guestSessionId, expiresAt)
}
