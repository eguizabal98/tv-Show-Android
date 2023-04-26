package com.eem.data.di

import com.eem.data.repository.authentication.AuthenticationRepositoryImpl
import com.eem.domain.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    fun provideIoDispatchers(): CoroutineDispatcher = Dispatchers.IO

    factory { provideIoDispatchers() }

    factoryOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
}
