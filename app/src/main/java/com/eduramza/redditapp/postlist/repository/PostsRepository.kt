package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.PostsDTO
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun fetchPosts(): Flow<Result<List<PostsDTO>>>
}
