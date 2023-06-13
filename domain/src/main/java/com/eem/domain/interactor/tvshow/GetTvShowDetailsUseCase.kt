package com.eem.domain.interactor.tvshow

import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.model.tvshow.TvShowDetails

interface GetTvShowDetailsUseCase {

    suspend operator fun invoke(tvShowId: String): ResultWrapper<TvShowDetails>
}
