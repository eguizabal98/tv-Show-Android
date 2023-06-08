package com.eem.domain.interactor.tvshow

import com.eem.domain.model.result.ResultWrapper

interface SetFilterUseCase {

    suspend operator fun invoke(filter: String): ResultWrapper<Boolean>
}
