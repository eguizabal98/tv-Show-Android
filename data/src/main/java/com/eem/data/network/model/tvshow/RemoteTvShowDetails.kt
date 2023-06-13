package com.eem.data.network.model.tvshow

import com.eem.data.database.model.tvshow.TvShowDetailsEntity
import com.eem.data.repository.base.RoomMapper
import com.google.gson.annotations.SerializedName

data class RemoteTvShowDetails(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("genres")
    val genres: List<RemoteTvShowGenre>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("seasons")
    val seasons: List<RemoteTvShowSeason>?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
) : RoomMapper<TvShowDetailsEntity> {

    override fun mapToRoomEntity(): TvShowDetailsEntity = TvShowDetailsEntity(
        id ?: 0,
        backdropPath ?: "",
        episodeRunTime ?: emptyList(),
        firstAirDate ?: "",
        genres?.map { it.mapToRoomEntity() } ?: emptyList(),
        lastAirDate ?: "",
        name ?: "",
        overview ?: "",
        popularity ?: 0.0,
        posterPath ?: "",
        seasons?.map { it.mapToRoomEntity() } ?: emptyList(),
        voteAverage ?: 0.0,
        voteCount ?: 0
    )
}
