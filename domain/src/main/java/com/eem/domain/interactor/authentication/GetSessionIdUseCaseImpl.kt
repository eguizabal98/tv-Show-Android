package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class GetSessionIdUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository
) : GetSessionIdUseCase {

    override suspend fun invoke(sessionIdRequest: SessionIdRequest): Flow<ResultWrapper<SessionId>> =
        authenticationRepository.getSessionId(sessionIdRequest)
}
