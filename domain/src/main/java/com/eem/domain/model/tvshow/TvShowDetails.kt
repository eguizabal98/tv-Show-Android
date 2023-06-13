package com.eem.domain.model.tvshow

data class TvShowDetails(
    val id: Int,
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<TvShowGenre>,
    val lastAirDate: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<TvShowSeason>,
    val voteAverage: Double,
    val voteCount: Int
) {

    fun getBackdropUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
    fun getPosterUrl() = "https://image.tmdb.org/t/p/w200$posterPath"
}
