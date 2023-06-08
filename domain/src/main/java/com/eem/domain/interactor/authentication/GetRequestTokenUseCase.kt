package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.result.ResultWrapper

interface GetRequestTokenUseCase {

    suspend operator fun invoke(): ResultWrapper<RequestToken>
}
