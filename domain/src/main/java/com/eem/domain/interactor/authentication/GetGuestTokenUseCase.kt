package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GetGuestTokenUseCase {

    suspend operator fun invoke(): Flow<ResultWrapper<GuestToken>>
}
