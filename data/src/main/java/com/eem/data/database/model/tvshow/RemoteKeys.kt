package com.eem.data.database.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val tvShowId: Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?
)
