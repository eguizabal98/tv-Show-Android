package com.eem.data.repository.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.eem.data.database.TvShowDataBase
import com.eem.data.database.model.tvshow.RemoteKeys
import com.eem.data.database.model.tvshow.TvShowInfoEntity
import com.eem.data.network.api.TvShowApiService
import com.eem.domain.model.tvshow.Filter
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class TvShowRemoteMediator(
    private val service: TvShowApiService,
    private val tvShowDataBase: TvShowDataBase
) : RemoteMediator<Int, TvShowInfoEntity>() {

    private var filter = ""

    override suspend fun initialize(): InitializeAction {
        filter = tvShowDataBase.lastFilterDao().getLastFilter()?.filter ?: Filter.TOP_RATED.filter
        return super.initialize()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowInfoEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    // New Query so clear the DB
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    val prevKey = remoteKeys?.prevKey
                    prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)

                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with endOfPaginationReached = false because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.
                    val nextKey = remoteKeys?.nextKey
                    nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val apiResponse = service.getPopularShows(filter, page)

            val tvShows = apiResponse.results
            val endOfPaginationReached = tvShows.isEmpty()

            tvShowDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    tvShowDataBase.remoteKeysDao().clearRemoteKeys()
                    tvShowDataBase.tvShowDao().clearAll()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = tvShows.map {
                    RemoteKeys(
                        tvShowId = it.tvShowId,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                tvShowDataBase.remoteKeysDao().insertAll(remoteKeys)
                val tvShowDB = tvShows.map { it.mapToRoomEntity() }
                tvShowDataBase.tvShowDao().insertAll(tvShowDB.onEachIndexed { _, tvShow -> tvShow.page = page })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    /** LoadType.Append
     * When we need to load data at the end of the currently loaded data set, the load parameter is LoadType.APPEND
     */
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TvShowInfoEntity>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { tvShow ->
            tvShowDataBase.remoteKeysDao().getRemoteKeyByTvShowID(tvShow.id)
        }
    }

    /** LoadType.Prepend
     * When we need to load data at the beginning of the currently loaded data set, the load parameter is LoadType.PREPEND
     */
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TvShowInfoEntity>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { tvShow ->
            tvShowDataBase.remoteKeysDao().getRemoteKeyByTvShowID(tvShow.id)
        }
    }

    /** LoadType.REFRESH
     * Gets called when it's the first time we're loading data, or when PagingDataAdapter.refresh() is called;
     * so now the point of reference for loading our data is the state.anchorPosition.
     * If this is the first load, then the anchorPosition is null.
     * When PagingDataAdapter.refresh() is called, the anchorPosition is the first visible position in the displayed list, so we will need to load the page that contains that specific item.
     */
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, TvShowInfoEntity>): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                tvShowDataBase.remoteKeysDao().getRemoteKeyByTvShowID(id)
            }
        }
    }
}
