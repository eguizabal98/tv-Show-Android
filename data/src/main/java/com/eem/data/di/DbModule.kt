package com.eem.data.di

import android.content.Context
import androidx.room.Room
import com.eem.data.database.TvShowDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    private const val DB_NAME = "TvShow.db"

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): TvShowDataBase {
        return Room.databaseBuilder(
            context,
            TvShowDataBase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSessionIdDao(database: TvShowDataBase) = database.sessionIdDao()

    @Provides
    fun provideTvShowDao(database: TvShowDataBase) = database.tvShowDao()

    @Provides
    fun provideRemoteKeysDao(database: TvShowDataBase) = database.remoteKeysDao()

    @Provides
    fun provideLastFilter(database: TvShowDataBase) = database.lastFilterDao()
}
