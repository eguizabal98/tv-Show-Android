package com.eem.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eem.data.database.model.tvshow.TvShowInfoEntity

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvUsers: List<TvShowInfoEntity>)

    @Query("SELECT * FROM TvShowInfoEntity Order By page")
    fun getShows(): PagingSource<Int, TvShowInfoEntity>

    @Query("DELETE FROM TvShowInfoEntity")
    suspend fun clearAll()
}
