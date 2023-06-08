package com.eem.data.database.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.tvshow.TvShowInfo

@Entity
data class TvShowInfoEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val score: Double,
    val airDate: String,
    val posterImage: String?,
    val backDropImage: String?,
    val description: String,
    var page: Int?
) : DomainMapper<TvShowInfo> {

    override fun mapToDomainModel(): TvShowInfo =
        TvShowInfo(id, name, score, airDate, posterImage, backDropImage, description)
}
