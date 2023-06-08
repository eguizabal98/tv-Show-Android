package com.eem.androidcommon.ui.di

import android.content.Context
import com.eem.androidcommon.ui.base.ResourceProvider
import com.eem.androidcommon.ui.base.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }
}
