package com.eem.remotedata.model.authentication

import com.eem.data.model.authentication.DataRequestToken
import com.eem.data.model.base.DataMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRequestToken(
    @SerialName("success")
    val success: Boolean,
    @SerialName("request_token")
    val requestToken: String,
    @SerialName("expires_at")
    val expiresAt: String
) : DataMapper<DataRequestToken>() {

    override fun mapToDataModel(): DataRequestToken =
        DataRequestToken(success, requestToken, expiresAt)
}
