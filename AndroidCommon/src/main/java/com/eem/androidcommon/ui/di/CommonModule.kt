package com.eem.androidcommon.ui.di

import com.eem.androidcommon.ui.base.ResourceProvider
import com.eem.androidcommon.ui.base.ResourceProviderImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {

    single { ResourceProviderImpl(get()) } bind ResourceProvider::class
}
