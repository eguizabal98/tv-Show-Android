package com.eem.data.network.api

import com.eem.data.network.model.tvshow.RemoteTvShowDetails
import com.eem.data.network.model.tvshow.RemoteTvShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {

    @GET("tv/{filter}")
    suspend fun getPopularShows(
        @Path("filter") filter: String,
        @Query("page") page: Int
    ): RemoteTvShows

    @GET("tv/{series_id}")
    suspend fun getTvShowDetails(
        @Path("series_id") showId: String
    ): Response<RemoteTvShowDetails>
}
