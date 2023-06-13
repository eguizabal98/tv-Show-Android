package com.eem.data.network.model.tvshow

import com.eem.data.database.model.tvshow.TvShowSeasonEntity
import com.eem.data.repository.base.RoomMapper
import com.google.gson.annotations.SerializedName

data class RemoteTvShowSeason(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int?
) : RoomMapper<TvShowSeasonEntity> {

    override fun mapToRoomEntity(): TvShowSeasonEntity =
        TvShowSeasonEntity(
            id ?: 0,
            airDate ?: "",
            episodeCount ?: 0,
            name ?: "",
            overview ?: "",
            posterPath ?: "",
            seasonNumber ?: 0
        )
}
