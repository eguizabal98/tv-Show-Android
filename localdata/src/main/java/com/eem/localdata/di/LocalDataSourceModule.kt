package com.eem.localdata.di

import com.eem.data.datasource.local.LocalTestSource
import com.eem.localdata.source.LocalTestSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val localDataSourceModule = module {

    factory { LocalTestSourceImpl(get()) } bind LocalTestSource::class
}
