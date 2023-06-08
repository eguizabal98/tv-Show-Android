package com.eem.data.network.model.authentication

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSessionIdRequest(
    @SerializedName("request_token")
    val requestToken: String
)
