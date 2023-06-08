package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class GetGuestTokenUseCaseImpl @Inject constructor(
    private val guestAuthenticationRepository: AuthenticationRepository
) : GetGuestTokenUseCase {

    override suspend fun invoke(): ResultWrapper<GuestToken> =
        guestAuthenticationRepository.getGuestToken()
}
