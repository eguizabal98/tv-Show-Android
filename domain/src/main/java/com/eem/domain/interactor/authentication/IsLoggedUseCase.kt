package com.eem.domain.interactor.authentication

import com.eem.domain.model.result.ResultWrapper

interface IsLoggedUseCase {

    suspend operator fun invoke(): ResultWrapper<Boolean>
}
