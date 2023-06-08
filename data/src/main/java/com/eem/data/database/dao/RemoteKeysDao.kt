package com.eem.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eem.data.database.model.tvshow.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("Select * From RemoteKeys Where tvShowId = :id")
    suspend fun getRemoteKeyByTvShowID(id: Int): RemoteKeys?

    @Query("Delete From RemoteKeys")
    suspend fun clearRemoteKeys()
}
