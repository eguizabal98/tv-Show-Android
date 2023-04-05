package com.eem.tvshowandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TvShowAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}