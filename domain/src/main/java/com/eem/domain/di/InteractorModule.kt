package com.eem.domain.di

import com.eem.domain.interactor.TestUseCase
import com.eem.domain.interactor.TestUseCaseImpl
import com.eem.domain.interactor.authentication.GetGuestTokenUseCase
import com.eem.domain.interactor.authentication.GetGuestTokenUseCaseImpl
import com.eem.domain.interactor.authentication.GetRequestTokenUseCase
import com.eem.domain.interactor.authentication.GetRequestTokenUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val interactorModule = module {

    factoryOf(::TestUseCaseImpl) bind TestUseCase::class
    factoryOf(::GetGuestTokenUseCaseImpl) bind GetGuestTokenUseCase::class
    factoryOf(::GetRequestTokenUseCaseImpl) bind GetRequestTokenUseCase::class
}
