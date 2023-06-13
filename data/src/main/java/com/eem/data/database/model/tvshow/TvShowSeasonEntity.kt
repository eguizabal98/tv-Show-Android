package com.eem.data.database.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.tvshow.TvShowSeason

@Entity
data class TvShowSeasonEntity(
    @PrimaryKey
    val id: Int,
    val airDate: String,
    val episodeCount: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
) : DomainMapper<TvShowSeason> {

    override fun mapToDomainModel(): TvShowSeason =
        TvShowSeason(id, airDate, episodeCount, name, overview, posterPath, seasonNumber)
}
