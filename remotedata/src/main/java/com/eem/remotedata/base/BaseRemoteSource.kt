package com.eem.remotedata.base

import com.eem.data.model.base.DataMapper
import com.eem.data.model.base.ResponseWrapper
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRemoteSource(private val dispatcher: CoroutineDispatcher) {

    fun <T : DataMapper<R>, R> fetchData(
        fetchRemote: suspend () -> T
    ): Flow<ResponseWrapper<R>> = flow {
        try {
            emit(ResponseWrapper.Success(fetchRemote().mapToDataModel()))
        } catch (throwable: Throwable) {
            emit(
                when (throwable) {
                    is IOException -> ResponseWrapper.NetworkError
                    is ClientRequestException -> getErrorResponseWrapper(throwable)
                    else -> ResponseWrapper.Error()
                }
            )
        }
    }.catch { throwable ->
        when (throwable) {
            is IOException -> ResponseWrapper.NetworkError
            is ClientRequestException -> getErrorResponseWrapper(throwable)
            else -> ResponseWrapper.Error()
        }
    }.flowOn(dispatcher)

    private suspend fun getErrorResponseWrapper(
        exception: ClientRequestException
    ): ResponseWrapper.Error {
        return ResponseWrapper.Error(
            exception.response.status.value,
            exception.response.body()
        )
    }
}
