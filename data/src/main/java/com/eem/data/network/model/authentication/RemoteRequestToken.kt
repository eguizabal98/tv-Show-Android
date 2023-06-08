package com.eem.data.network.model.authentication

import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.authentication.RequestToken
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRequestToken(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("expires_at")
    val expiresAt: String
) : DomainMapper<RequestToken> {

    override fun mapToDomainModel(): RequestToken =
        RequestToken(requestToken)
}
