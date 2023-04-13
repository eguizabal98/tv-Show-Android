package com.eem.remotedata.di

import com.eem.data.datasource.remote.RemoteTestSource
import com.eem.remotedata.source.RemoteTestSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteDataSourceModule = module {

    factory { RemoteTestSourceImpl(get()) } bind RemoteTestSource::class
}
