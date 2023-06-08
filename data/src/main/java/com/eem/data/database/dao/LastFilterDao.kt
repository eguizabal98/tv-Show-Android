package com.eem.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.eem.data.database.model.tvshow.LastFilter

@Dao
interface LastFilterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lastFilter: LastFilter)

    @Query("Select * From LastFilter LIMIT 1")
    suspend fun getLastFilter(): LastFilter?

    @Query("Delete From LastFilter")
    suspend fun clearLastFilter()

    @Transaction
    suspend fun insertAndDeleteInTransaction(lastFilter: LastFilter) {
        clearLastFilter()
        insert(lastFilter)
    }
}
