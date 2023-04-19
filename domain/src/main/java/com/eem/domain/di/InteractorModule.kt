package com.eem.domain.di

import com.eem.domain.interactor.TestUseCase
import com.eem.domain.interactor.TestUseCaseImpl
import com.eem.domain.interactor.authentication.GetGuestTokenUseCase
import com.eem.domain.interactor.authentication.GetGuestTokenUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val interactorModule = module {

    factory { TestUseCaseImpl(get()) } bind TestUseCase::class
    factory { GetGuestTokenUseCaseImpl(get()) } bind GetGuestTokenUseCase::class
}
