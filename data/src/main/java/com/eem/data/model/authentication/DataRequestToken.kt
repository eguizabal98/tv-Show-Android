package com.eem.data.model.authentication

import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.base.DomainMapper

data class DataRequestToken(
    val success: Boolean,
    val requestToken: String,
    val expiresAt: String
) : DomainMapper<RequestToken>() {

    override fun mapToDomainModel(): RequestToken = RequestToken(requestToken)
}
