package com.eem.data.network.model.authentication

import com.eem.data.database.model.authentication.DataSessionIdEntity
import com.eem.data.repository.base.RoomMapper
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSessionId(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("session_id")
    val sessionId: String
) : RoomMapper<DataSessionIdEntity> {

    override fun mapToRoomEntity(): DataSessionIdEntity = DataSessionIdEntity(sessionId)
}
