package com.eem.data.di

import com.eem.data.repository.TestRepositoryImpl
import com.eem.data.repository.authentication.GuestAuthenticationRepositoryImpl
import com.eem.domain.repository.TestRepository
import com.eem.domain.repository.authentication.GuestAuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    fun provideIoDispatchers(): CoroutineDispatcher = Dispatchers.IO

    factory { provideIoDispatchers() }

    factory { TestRepositoryImpl(get(), get(), get()) } bind TestRepository::class
    factory { GuestAuthenticationRepositoryImpl(get(), get()) } bind GuestAuthenticationRepository::class
}
