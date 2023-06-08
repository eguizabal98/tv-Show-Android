package com.eem.domain.di

import com.eem.domain.interactor.authentication.GetGuestTokenUseCase
import com.eem.domain.interactor.authentication.GetGuestTokenUseCaseImpl
import com.eem.domain.interactor.authentication.GetRequestTokenUseCase
import com.eem.domain.interactor.authentication.GetRequestTokenUseCaseImpl
import com.eem.domain.interactor.authentication.GetSessionIdUseCase
import com.eem.domain.interactor.authentication.GetSessionIdUseCaseImpl
import com.eem.domain.interactor.authentication.IsLoggedUseCase
import com.eem.domain.interactor.authentication.IsLoggedUseCaseImpl
import com.eem.domain.interactor.tvshow.GetLastFilterUseCase
import com.eem.domain.interactor.tvshow.GetLastFilterUseCaseImpl
import com.eem.domain.interactor.tvshow.GetTvShowUseCase
import com.eem.domain.interactor.tvshow.GetTvShowUseCaseImpl
import com.eem.domain.interactor.tvshow.SetFilterUseCase
import com.eem.domain.interactor.tvshow.SetFilterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindGetGuestTokenUseCase(impl: GetGuestTokenUseCaseImpl): GetGuestTokenUseCase

    @Binds
    abstract fun bindGetRequestTokenUseCase(impl: GetRequestTokenUseCaseImpl): GetRequestTokenUseCase

    @Binds
    abstract fun bindGetSessionIdUseCase(impl: GetSessionIdUseCaseImpl): GetSessionIdUseCase

    @Binds
    abstract fun bindIsLoggedUseCase(impl: IsLoggedUseCaseImpl): IsLoggedUseCase

    @Binds
    abstract fun bindGetTvShowUseCase(impl: GetTvShowUseCaseImpl): GetTvShowUseCase

    @Binds
    abstract fun bindGetLastFilterUseCase(impl: GetLastFilterUseCaseImpl): GetLastFilterUseCase

    @Binds
    abstract fun bindSetFilterUseCase(impl: SetFilterUseCaseImpl): SetFilterUseCase
}
