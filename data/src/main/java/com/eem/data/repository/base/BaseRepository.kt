package com.eem.data.repository.base

import com.eem.data.network.networkstate.Connectivity
import com.eem.domain.model.result.Failure
import com.eem.domain.model.result.HttpError
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.model.result.Success
import kotlinx.coroutines.withContext

abstract class BaseRepository(
    private val connectivity: Connectivity,
    private val contextProvider: CoroutineContextProvider
) {

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun <T, R : DomainMapper<T>> fetchData(
        apiDataProvider: suspend () -> ResultWrapper<T>,
        dbDataProvider: suspend () -> R?
    ): ResultWrapper<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) {
                    Success(dbResult.mapToDomainModel())
                } else {
                    Failure(
                        HttpError(
                            Throwable(DB_ENTRY_ERROR)
                        )
                    )
                }
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun <T : Any> fetchData(dataProvider: suspend () -> ResultWrapper<T>): ResultWrapper<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }

    /**
     * Use this when communicating only with cache
     */
    protected suspend fun <T> fetchCacheData(dbDataProvider: suspend () -> T?): ResultWrapper<T> {
        return withContext(contextProvider.io) {
            val dbResult = dbDataProvider()
            if (dbResult != null) {
                Success(dbResult)
            } else {
                Failure(
                    HttpError(
                        Throwable(DB_ENTRY_ERROR)
                    )
                )
            }
        }
    }
}
