package com.eem.domain.interactor.tvshow

import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.model.tvshow.TvShowDetails
import com.eem.domain.repository.tvshow.TvShowRepository
import javax.inject.Inject

class GetTvShowDetailsUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetTvShowDetailsUseCase {

    override suspend fun invoke(tvShowId: String): ResultWrapper<TvShowDetails> =
        tvShowRepository.getTvShowDetails(tvShowId)
}
