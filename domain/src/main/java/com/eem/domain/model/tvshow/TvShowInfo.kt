package com.eem.domain.model.tvshow

data class TvShowInfo(
    val tvShowId: Int,
    val name: String,
    val score: Double,
    val airDate: String,
    val posterImage: String?,
    val backDropImage: String?,
    val description: String
)
