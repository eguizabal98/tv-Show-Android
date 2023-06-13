package com.eem.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eem.data.database.model.tvshow.TvShowDetailsEntity

@Dao
interface TvShowDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShowDetails: TvShowDetailsEntity)

    @Query("SELECT * FROM TvShowDetailsEntity WHERE id = :tvShowId LIMIT 1")
    fun getShowsDetails(tvShowId: String): TvShowDetailsEntity

    @Query("DELETE FROM TvShowDetailsEntity")
    suspend fun clearAll()
}
