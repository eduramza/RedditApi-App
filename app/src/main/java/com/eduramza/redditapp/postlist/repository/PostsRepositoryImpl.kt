package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.service.RedditServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsRepositoryImpl(private val api: RedditServiceApi) : PostsRepository {
    override suspend fun fetchPosts(): Flow<Result<List<PostsDTO>>> {
        val response = Result.success(api.getListOfPost())
        return flow { }
    }
}