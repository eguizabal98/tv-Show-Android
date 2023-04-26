package com.eem.localdata.di

import com.eem.data.datasource.local.LocalAuthenticationSource
import com.eem.data.datasource.local.LocalTestSource
import com.eem.localdata.source.LocalAuthenticationSourceImpl
import com.eem.localdata.source.LocalTestSourceImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localDataSourceModule = module {

    factory { LocalTestSourceImpl(get()) } bind LocalTestSource::class
    factoryOf(::LocalAuthenticationSourceImpl) bind LocalAuthenticationSource::class
}
