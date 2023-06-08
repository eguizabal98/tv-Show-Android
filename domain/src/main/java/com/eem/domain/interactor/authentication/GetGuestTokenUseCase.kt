package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.result.ResultWrapper

interface GetGuestTokenUseCase {

    suspend operator fun invoke(): ResultWrapper<GuestToken>
}
