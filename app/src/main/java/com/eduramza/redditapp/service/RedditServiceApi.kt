package com.eduramza.redditapp.service

import com.eduramza.redditapp.domain.detail.DetailRootResponse
import com.eduramza.redditapp.domain.list.ListPostResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RedditServiceApi {
    @GET("r/{topic}.json")
    suspend fun getListOfPost(@Path("topic") topic: String): ListPostResponse

    @GET("")
    suspend fun getPostDetails(@Url permalink: String): DetailRootResponse

    @GET("r/{topic}.json")
    suspend fun getListNextPage(@Path("topic") topic: String,
                                @Query("after") after: String): ListPostResponse
}
