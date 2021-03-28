package com.eduramza.redditapp.postdetails.repository

import com.eduramza.redditapp.domain.detail.PostDetailResponse
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun fetchSelectedPost(permalink: String): Flow<Result<PostDetailResponse>>
}