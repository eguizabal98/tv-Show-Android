package com.eem.data.network.api

import com.eem.data.network.model.tvshow.RemoteTvShows
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {

    @GET("tv/{filter}")
    suspend fun getPopularShows(
        @Path("filter") filter: String,
        @Query("page") page: Int
    ): RemoteTvShows
}
