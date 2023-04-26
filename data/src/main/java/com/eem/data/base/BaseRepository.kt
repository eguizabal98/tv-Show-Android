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

    fun <T : Flow<ResponseWrapper<X>>, X : DomainMapper<S>, S> localAndFetch(
        localSourceRequest: suspend () -> X?,
        localSourceAction: suspend (X) -> X?,
        remoteSourceRequest: suspend () -> T,
        shouldFetch: suspend (X?) -> Boolean
    ): Flow<ResultWrapper<S>> = flow {
        val localResponse = localSourceRequest()
        if (shouldFetch(localResponse)) {
            when (val remoteResponse = remoteSourceRequest().single()) {
                is ResponseWrapper.Success -> {
                    val localResult = sanityResponse(localSourceAction(remoteResponse.data)?.mapToDomainModel())
                    emit(localResult)
                }
                is ResponseWrapper.Error -> emit(ResultWrapper.Error(remoteResponse.error?.message))
                is ResponseWrapper.NetworkError -> emit(ResultWrapper.NetworkError)
            }
        } else {
            emit(sanityResponse(localResponse?.mapToDomainModel()))
        }
    }.onStart {
        emit(ResultWrapper.Loading)
    }.flowOn(dispatcher)

    fun <T : Flow<ResponseWrapper<X>>, X : DomainMapper<S>, S> fetchAndSave(
        remoteSourceRequest: suspend () -> T,
        localSourceAction: suspend (X) -> X?
    ): Flow<ResultWrapper<S>> = flow {
        when (val remoteResponse = remoteSourceRequest().single()) {
            is ResponseWrapper.Success -> {
                val localResult = sanityResponse(localSourceAction(remoteResponse.data)?.mapToDomainModel())
                emit(localResult)
            }
            is ResponseWrapper.Error -> emit(ResultWrapper.Error(remoteResponse.error?.message))
            is ResponseWrapper.NetworkError -> emit(ResultWrapper.NetworkError)
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
