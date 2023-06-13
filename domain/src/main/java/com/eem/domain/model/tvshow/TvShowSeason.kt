package com.eem.domain.model.tvshow

data class TvShowSeason(
    val id: Int,
    val airDate: String,
    val episodeCount: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
)
