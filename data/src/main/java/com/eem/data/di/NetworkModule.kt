package com.eem.data.di

import android.content.Context
import com.eem.data.network.api.AuthApiService
import com.eem.data.network.api.TvShowApiService
import com.eem.data.network.getApiKeyInterceptor
import com.eem.data.network.getLoggerInterceptor
import com.eem.data.network.networkstate.Connectivity
import com.eem.data.network.networkstate.ConnectivityImpl
import com.eem.data.network.utils.buildApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CERTIFICATE_HOST_NAME = "api.themoviedb.org"

    @Singleton
    @Provides
    fun provideMovieApiCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add(CERTIFICATE_HOST_NAME, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(CERTIFICATE_HOST_NAME, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(CERTIFICATE_HOST_NAME, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthenticationApi(certificatePinner: CertificatePinner): AuthApiService =
        buildApi(
            certificatePinner = certificatePinner,
            interceptors = listOf(getApiKeyInterceptor(), getLoggerInterceptor()),
            api = AuthApiService::class.java
        )

    @Singleton
    @Provides
    fun provideTvShowApi(certificatePinner: CertificatePinner): TvShowApiService =
        buildApi(
            certificatePinner = certificatePinner,
            interceptors = listOf(getApiKeyInterceptor(), getLoggerInterceptor()),
            api = TvShowApiService::class.java
        )

    @Singleton
    @Provides
    fun provideConnectivity(@ApplicationContext context: Context): Connectivity =
        ConnectivityImpl(context)
}
