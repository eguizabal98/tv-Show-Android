package com.eem.domain.interactor.tvshow

import com.eem.domain.model.result.ResultWrapper

interface GetLastFilterUseCase {

    suspend operator fun invoke(): ResultWrapper<String>
}
