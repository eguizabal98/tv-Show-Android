package com.eem.domain.interactor.tvshow

import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.tvshow.TvShowRepository
import javax.inject.Inject

class GetLastFilterUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetLastFilterUseCase {

    override suspend fun invoke(): ResultWrapper<String> = tvShowRepository.getLastFilter()
}
