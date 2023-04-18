package com.eem.data.base

import com.eem.data.model.ResponseWrapper
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single

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

    private fun <T> sanityResponse(value: T?): ResultWrapper<T> =
        if (value != null) ResultWrapper.Success(value) else ResultWrapper.Error("Null Values")
}
