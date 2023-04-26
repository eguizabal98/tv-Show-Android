package com.eem.remotedata.di

import com.eem.data.datasource.remote.RemoteAuthenticationSource
import com.eem.remotedata.source.RemoteAuthenticationSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteDataSourceModule = module {

    factory { RemoteAuthenticationSourceImpl(get(), get()) } bind RemoteAuthenticationSource::class
}
