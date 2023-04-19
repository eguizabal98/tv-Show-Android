package com.eem.data.base

import com.eem.data.model.base.ResponseWrapper
import com.eem.domain.model.base.DomainMapper
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.transform

abstract class BaseRepository(private val dispatcher: CoroutineDispatcher) {

    fun <T> localAndFetch(
        localSourceRequest: suspend () -> T?,
        localSourceAction: suspend (T) -> T?,
        remoteSourceRequest: suspend () -> Flow<ResponseWrapper<T>>,
        shouldFetch: suspend (T?) -> Boolean
    ): Flow<ResultWrapper<T>> = flow {
        val localResponse = localSourceRequest()
        if (shouldFetch(localResponse)) {
            when (val remoteResponse = remoteSourceRequest().single()) {
                is ResponseWrapper.Success -> {
                    val localResult = sanityResponse(localSourceAction(remoteResponse.data))
                    emit(localResult)
                }
                is ResponseWrapper.Error -> emit(ResultWrapper.Error(remoteResponse.error?.message))
                is ResponseWrapper.NetworkError -> emit(ResultWrapper.NetworkError)
            }
        } else {
            emit(sanityResponse(localResponse))
        }
    }.onStart {
        emit(ResultWrapper.Loading)
    }.flowOn(dispatcher)

    suspend fun <T : Flow<ResponseWrapper<R>>, R : DomainMapper<W>, W> onlyRemoteFetch(
        remoteSourceRequest: suspend () -> T
    ): Flow<ResultWrapper<W>> {
        return remoteSourceRequest().transform { value ->
            when (value) {
                is ResponseWrapper.Error -> emit(ResultWrapper.Error(value.error?.message))
                is ResponseWrapper.NetworkError -> emit(ResultWrapper.NetworkError)
                is ResponseWrapper.Success -> emit(ResultWrapper.Success(value.data.mapToDomainModel()))
            }
        }.onStart {
            emit(ResultWrapper.Loading)
        }.flowOn(dispatcher)
    }

    private fun <T> sanityResponse(value: T?): ResultWrapper<T> =
        if (value != null) ResultWrapper.Success(value) else ResultWrapper.Error("Null Values")
}
