package com.eem.domain.interactor.tvshow

import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.tvshow.TvShowRepository
import javax.inject.Inject

class SetFilterUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : SetFilterUseCase {

    override suspend fun invoke(filter: String): ResultWrapper<Boolean> =
        tvShowRepository.setLastFilter(filter)
}
