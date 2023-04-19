package com.eem.data.repository.authentication

import com.eem.data.base.BaseRepository
import com.eem.data.datasource.remote.RemoteAuthenticationSource
import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.GuestAuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GuestAuthenticationRepositoryImpl(
    private val remoteAuthenticationSource: RemoteAuthenticationSource,
    dispatcher: CoroutineDispatcher
) : GuestAuthenticationRepository, BaseRepository(dispatcher) {

    override suspend fun getGuestToken(): Flow<ResultWrapper<GuestToken>> = onlyRemoteFetch {
        remoteAuthenticationSource.getGuestToken()
    }
}
