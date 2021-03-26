package com.eduramza.redditapp.service

import com.eduramza.redditapp.domain.PostsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditServiceApi {
    @GET("r/{topic}.json")
    suspend fun getListOfPost(@Path("topic") topic: String): PostsResponse
}
