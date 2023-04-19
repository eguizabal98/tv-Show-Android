package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.GuestAuthenticationRepository
import kotlinx.coroutines.flow.Flow

class GetGuestTokenUseCaseImpl(
    private val guestAuthenticationRepository: GuestAuthenticationRepository
) : GetGuestTokenUseCase {

    override suspend fun invoke(): Flow<ResultWrapper<GuestToken>> =
        guestAuthenticationRepository.getGuestToken()
}
