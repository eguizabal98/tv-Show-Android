package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper

interface GetSessionIdUseCase {

    suspend operator fun invoke(sessionIdRequest: SessionIdRequest): ResultWrapper<SessionId>
}
