package com.eem.data.di

import com.eem.data.repository.TestRepositoryImpl
import com.eem.domain.repository.TestRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    factory { TestRepositoryImpl(get(), get()) } bind TestRepository::class
}
