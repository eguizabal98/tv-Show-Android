package com.eem.data.di

import com.eem.data.database.TvShowDataBase
import com.eem.data.database.dao.LastFilterDao
import com.eem.data.database.dao.SessionIdDao
import com.eem.data.database.dao.TvShowDao
import com.eem.data.network.api.AuthApiService
import com.eem.data.network.api.TvShowApiService
import com.eem.data.network.networkstate.Connectivity
import com.eem.data.repository.authentication.AuthenticationRepositoryImpl
import com.eem.data.repository.base.CoroutineContextProvider
import com.eem.data.repository.tvshow.TvShowRepositoryImpl
import com.eem.domain.repository.authentication.AuthenticationRepository
import com.eem.domain.repository.tvshow.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideIoDispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideAuthenticationRepository(
        authApiService: AuthApiService,
        sessionIdDao: SessionIdDao,
        connectivity: Connectivity,
        contextProvider: CoroutineContextProvider
    ): AuthenticationRepository = AuthenticationRepositoryImpl(authApiService, sessionIdDao, connectivity, contextProvider)

    @Provides
    fun provideTvShowRepository(
        service: TvShowApiService,
        tvShowDao: TvShowDao,
        tvShowDataBase: TvShowDataBase,
        lastFilterDao: LastFilterDao
    ): TvShowRepository = TvShowRepositoryImpl(service, tvShowDao, tvShowDataBase, lastFilterDao)
}
