package com.eem.data.network.model.authentication

import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.authentication.GuestToken
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGuestToken(

    @SerializedName("success")
    val success: Boolean,
    @SerializedName("guest_session_id")
    val guestSessionId: String,
    @SerializedName("expires_at")
    val expiresAt: String
) : DomainMapper<GuestToken> {

    override fun mapToDomainModel(): GuestToken = GuestToken(guestSessionId)
}
