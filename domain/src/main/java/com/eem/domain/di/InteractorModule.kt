package com.eem.domain.di

import com.eem.domain.interactor.authentication.GetGuestTokenUseCase
import com.eem.domain.interactor.authentication.GetGuestTokenUseCaseImpl
import com.eem.domain.interactor.authentication.GetRequestTokenUseCase
import com.eem.domain.interactor.authentication.GetRequestTokenUseCaseImpl
import com.eem.domain.interactor.authentication.GetSessionIdUseCase
import com.eem.domain.interactor.authentication.GetSessionIdUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val interactorModule = module {

    factoryOf(::GetGuestTokenUseCaseImpl) bind GetGuestTokenUseCase::class
    factoryOf(::GetRequestTokenUseCaseImpl) bind GetRequestTokenUseCase::class
    factoryOf(::GetSessionIdUseCaseImpl) bind GetSessionIdUseCase::class
}
