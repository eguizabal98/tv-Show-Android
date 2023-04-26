package com.eem.remotedata.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSessionIdRequest(
    @SerialName("request_token")
    val requestToken: String
)
