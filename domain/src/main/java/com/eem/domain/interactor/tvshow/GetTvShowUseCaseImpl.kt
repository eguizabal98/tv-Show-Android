package com.eem.domain.interactor.tvshow

import androidx.paging.PagingData
import com.eem.domain.model.tvshow.TvShowInfo
import com.eem.domain.repository.tvshow.TvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvShowUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetTvShowUseCase {

    override fun invoke(): Flow<PagingData<TvShowInfo>> = tvShowRepository.getShows()
}
