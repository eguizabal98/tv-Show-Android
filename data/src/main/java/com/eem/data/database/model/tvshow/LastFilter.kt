package com.eem.data.database.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LastFilter(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val filter: String
)
