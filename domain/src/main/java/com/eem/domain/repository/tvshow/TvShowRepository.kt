package com.eem.domain.repository.tvshow

import androidx.paging.PagingData
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.model.tvshow.TvShowDetails
import com.eem.domain.model.tvshow.TvShowInfo
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getShows(): Flow<PagingData<TvShowInfo>>

    suspend fun getLastFilter(): ResultWrapper<String>

    suspend fun setLastFilter(filter: String): ResultWrapper<Boolean>

    suspend fun getTvShowDetails(tvShowId: String): ResultWrapper<TvShowDetails>
}
