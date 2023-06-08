package com.eem.data.repository.base

import com.eem.domain.model.result.Failure
import com.eem.domain.model.result.HttpError
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.model.result.Success
import okio.IOException
import retrofit2.Response

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R?
): ResultWrapper<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) {
                Success(cachedModel.mapToDomainModel())
            } else {
                Failure(
                    HttpError(
                        Throwable(DB_ENTRY_ERROR)
                    )
                )
            }
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): ResultWrapper<R> {
    try {
        onSuccess { return Success(it.mapToDomainModel()) }
        onFailure { return Failure(it) }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

const val GENERAL_NETWORK_ERROR = "Something went wrong, please try again."
const val DB_ENTRY_ERROR = "No entry found in database"
