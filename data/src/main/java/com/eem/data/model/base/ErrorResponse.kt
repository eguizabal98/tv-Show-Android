package com.eem.data.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(

    @SerialName("status_message")
    val message: String
)
