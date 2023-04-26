package com.eem.remotedata.di

import com.eem.remotedata.api.AuthApiService
import com.eem.remotedata.api.AuthApiServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val apiModule = module {

    factory { AuthApiServiceImpl(get()) } bind AuthApiService::class
}
