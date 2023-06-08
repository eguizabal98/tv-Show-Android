package com.eem.data.repository.authentication

import com.eem.data.database.dao.SessionIdDao
import com.eem.data.network.api.AuthApiService
import com.eem.data.network.model.authentication.RemoteSessionIdRequest
import com.eem.data.network.networkstate.Connectivity
import com.eem.data.repository.base.BaseRepository
import com.eem.data.repository.base.CoroutineContextProvider
import com.eem.data.repository.base.getData
import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val sessionIdDao: SessionIdDao,
    connectivity: Connectivity,
    contextProvider: CoroutineContextProvider
) : AuthenticationRepository, BaseRepository(connectivity, contextProvider) {

    override suspend fun getGuestToken(): ResultWrapper<GuestToken> = fetchData {
        authApiService.getGuestToken().getData()
    }

    override suspend fun getRequestToken(): ResultWrapper<RequestToken> = fetchData {
        authApiService.getRequestToken().getData()
    }

    override suspend fun getSessionId(sessionIdRequest: SessionIdRequest): ResultWrapper<SessionId> {
        return fetchData(
            apiDataProvider = {
                authApiService.createSession(RemoteSessionIdRequest(sessionIdRequest.requestToken)).getData(
                    fetchFromCacheAction = { null },
                    cacheAction = {
                        sessionIdDao.clearSessionId()
                        sessionIdDao.insertSessionId(it)
                    }
                )
            },
            dbDataProvider = {
                null
            }
        )
    }

    override suspend fun isLogged(): ResultWrapper<Boolean> = fetchCacheData {
        sessionIdDao.getSessionId() != null
    }
}
