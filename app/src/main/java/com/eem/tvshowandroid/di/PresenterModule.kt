package com.eem.tvshowandroid.di

import com.eem.data.repository.base.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresenterModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }
}
