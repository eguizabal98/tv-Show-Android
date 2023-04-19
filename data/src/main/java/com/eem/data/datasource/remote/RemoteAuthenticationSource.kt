package com.eem.data.datasource.remote

import com.eem.data.model.authentication.DataGuestToken
import com.eem.data.model.base.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface RemoteAuthenticationSource {

    suspend fun getGuestToken(): Flow<ResponseWrapper<DataGuestToken>>
}
