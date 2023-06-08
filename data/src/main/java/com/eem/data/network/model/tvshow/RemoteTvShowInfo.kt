package com.eem.data.network.model.tvshow

import com.eem.data.database.model.tvshow.TvShowInfoEntity
import com.eem.data.repository.base.RoomMapper
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTvShowInfo(
    @SerializedName("id")
    val tvShowId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("vote_average")
    val score: Double,
    @SerializedName("first_air_date")
    val airDate: String,
    @SerializedName("poster_path")
    val posterImage: String?,
    @SerializedName("backdrop_path")
    val backDropImage: String?,
    @SerializedName("overview")
    val description: String
) : RoomMapper<TvShowInfoEntity> {

    override fun mapToRoomEntity(): TvShowInfoEntity =
        TvShowInfoEntity(
            id = tvShowId,
            name = name,
            score = score,
            airDate = airDate,
            posterImage = posterImage,
            backDropImage = backDropImage,
            description = description,
            page = null
        )
}
