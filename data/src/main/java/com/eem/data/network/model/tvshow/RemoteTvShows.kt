package com.eem.data.network.model.tvshow

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTvShows(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<RemoteTvShowInfo>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
