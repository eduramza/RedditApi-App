package com.eduramza.redditapp.service

import com.eduramza.redditapp.domain.detail.PostDetailResponse
import com.eduramza.redditapp.domain.list.PostsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditServiceApi {
    @GET("r/{topic}.json")
    suspend fun getListOfPost(@Path("topic") topic: String): PostsResponse

    @GET("{permalink}")
    suspend fun getPostDetails(@Path("permalink") permalink: String): PostDetailResponse
}
