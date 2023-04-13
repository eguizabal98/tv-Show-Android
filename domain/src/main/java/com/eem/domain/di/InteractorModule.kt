package com.eem.domain.di

import com.eem.domain.interactor.TestUseCase
import com.eem.domain.interactor.TestUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val interactorModule = module {

    factory { TestUseCaseImpl(get()) } bind TestUseCase::class
}
