package com.eem.tvshowandroid

import android.app.Application
import com.eem.androidcommon.ui.di.commonModule
import com.eem.authentication.di.authUiModule
import com.eem.data.di.repositoryModule
import com.eem.domain.di.interactorModule
import com.eem.localdata.di.dbModule
import com.eem.localdata.di.localDataSourceModule
import com.eem.remotedata.di.apiModule
import com.eem.remotedata.di.networkModule
import com.eem.remotedata.di.remoteDataSourceModule
import com.eem.tvshowandroid.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TvShowAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@TvShowAndroidApplication)
            // Load modules
            modules(
                listOf(
                    interactorModule,
                    repositoryModule,
                    apiModule,
                    networkModule,
                    remoteDataSourceModule,
                    localDataSourceModule,
                    presenterModule,
                    commonModule,
                    dbModule,
                    authUiModule
                )
            )
        }
    }
}
