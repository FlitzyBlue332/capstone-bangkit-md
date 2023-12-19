package com.capstone.sinari.data.retrofit

import com.capstone.sinari.data.response.NewsResponse
import com.capstone.sinari.data.response.TopicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("headlines")
    suspend fun getTopic(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): TopicResponse

    @GET("headlines/{referer}")
    suspend fun getNews(
        @Path("referer") referer: String
    ): NewsResponse
}