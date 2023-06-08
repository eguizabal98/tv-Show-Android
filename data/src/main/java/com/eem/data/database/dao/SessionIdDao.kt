package com.eem.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eem.data.database.model.authentication.DataSessionIdEntity

@Dao
interface SessionIdDao {

    @Query("SELECT * FROM DataSessionIdEntity")
    suspend fun getSessionId(): DataSessionIdEntity?

    @Insert
    suspend fun insertSessionId(dataSessionIdEntity: DataSessionIdEntity)

    @Query("DELETE FROM DataSessionIdEntity")
    suspend fun clearSessionId()
}
