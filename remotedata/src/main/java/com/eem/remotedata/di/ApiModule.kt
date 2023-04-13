package com.eem.remotedata.di

import com.eem.remotedata.api.TestApiService
import com.eem.remotedata.api.TestApiServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val apiModule = module {

    factory { TestApiServiceImpl(get()) } bind TestApiService::class
}
