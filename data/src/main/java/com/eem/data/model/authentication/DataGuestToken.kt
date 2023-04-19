package com.eem.data.model.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.base.DomainMapper

data class DataGuestToken(
    val success: Boolean,
    val guestSessionId: String,
    val expiresAt: String
) : DomainMapper<GuestToken>() {

    override fun mapToDomainModel(): GuestToken = GuestToken(guestSessionId)
}
