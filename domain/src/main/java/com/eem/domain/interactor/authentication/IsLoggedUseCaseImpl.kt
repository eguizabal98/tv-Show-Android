package com.eem.domain.interactor.authentication

import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class IsLoggedUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : IsLoggedUseCase {

    override suspend fun invoke(): ResultWrapper<Boolean> =
        authenticationRepository.isLogged()
}
