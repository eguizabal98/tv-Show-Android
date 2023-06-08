package com.eem.domain.interactor.tvshow

import androidx.paging.PagingData
import com.eem.domain.model.tvshow.TvShowInfo
import kotlinx.coroutines.flow.Flow

interface GetTvShowUseCase {

    operator fun invoke(): Flow<PagingData<TvShowInfo>>
}
