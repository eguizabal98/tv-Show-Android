package com.eem.data.repository.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.eem.data.database.TvShowDataBase
import com.eem.data.database.dao.LastFilterDao
import com.eem.data.database.dao.TvShowDao
import com.eem.data.database.model.tvshow.LastFilter
import com.eem.data.network.api.TvShowApiService
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.model.result.Success
import com.eem.domain.model.tvshow.Filter
import com.eem.domain.model.tvshow.TvShowInfo
import com.eem.domain.repository.tvshow.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class TvShowRepositoryImpl(
    private val service: TvShowApiService,
    private val tvShowDao: TvShowDao,
    private val tvShowDataBase: TvShowDataBase,
    private val lastFilterDao: LastFilterDao
) : TvShowRepository {

    override fun getShows(): Flow<PagingData<TvShowInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = TvShowRemoteMediator(service, tvShowDataBase)
        ) {
            tvShowDao.getShows()
        }.flow.map { paging ->
            paging.map { it.mapToDomainModel() }
        }
    }

    override suspend fun getLastFilter(): ResultWrapper<String> {
        return lastFilterDao.getLastFilter()?.filter?.let {
            Success(it)
        } ?: run {
            Success(Filter.TOP_RATED.filter)
        }
    }

    override suspend fun setLastFilter(filter: String): ResultWrapper<Boolean> {
        return if (lastFilterDao.getLastFilter()?.filter == filter) {
            Success(false)
        } else {
            lastFilterDao.insertAndDeleteInTransaction(LastFilter(null, filter))
            Success(true)
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
