package com.eem.data.database.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eem.data.repository.base.DomainMapper
import com.eem.domain.model.tvshow.TvShowDetails

@Entity
data class TvShowDetailsEntity(
    @PrimaryKey
    val id: Int,
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<TvShowGenreEntity>,
    val lastAirDate: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<TvShowSeasonEntity>,
    val voteAverage: Double,
    val voteCount: Int
) : DomainMapper<TvShowDetails> {

    override fun mapToDomainModel(): TvShowDetails = TvShowDetails(
        id,
        backdropPath,
        episodeRunTime,
        firstAirDate,
        genres.map { it.mapToDomainModel() },
        lastAirDate,
        name,
        overview,
        popularity,
        posterPath,
        seasons.map { it.mapToDomainModel() },
        voteAverage,
        voteCount
    )
}
