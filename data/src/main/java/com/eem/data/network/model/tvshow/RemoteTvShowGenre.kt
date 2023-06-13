package com.eem.data.network.model.tvshow

import com.eem.data.database.model.tvshow.TvShowGenreEntity
import com.eem.data.repository.base.RoomMapper
import com.google.gson.annotations.SerializedName

data class RemoteTvShowGenre(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
) : RoomMapper<TvShowGenreEntity> {

    override fun mapToRoomEntity(): TvShowGenreEntity = TvShowGenreEntity(id ?: 0, name ?: "")
}
