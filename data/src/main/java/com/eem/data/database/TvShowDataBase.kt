package com.eem.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eem.data.database.dao.LastFilterDao
import com.eem.data.database.dao.RemoteKeysDao
import com.eem.data.database.dao.SessionIdDao
import com.eem.data.database.dao.TvShowDao
import com.eem.data.database.model.authentication.DataSessionIdEntity
import com.eem.data.database.model.tvshow.LastFilter
import com.eem.data.database.model.tvshow.RemoteKeys
import com.eem.data.database.model.tvshow.TvShowInfoEntity

@Database(
    entities = [DataSessionIdEntity::class, TvShowInfoEntity::class, RemoteKeys::class, LastFilter::class],
    version = 3
)
abstract class TvShowDataBase : RoomDatabase() {
    abstract fun sessionIdDao(): SessionIdDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun lastFilterDao(): LastFilterDao
}
