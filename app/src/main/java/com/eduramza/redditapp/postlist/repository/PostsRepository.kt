package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.list.PostsDTO
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun fetchPosts(topic: String): Flow<Result<List<PostsDTO>>>
}
