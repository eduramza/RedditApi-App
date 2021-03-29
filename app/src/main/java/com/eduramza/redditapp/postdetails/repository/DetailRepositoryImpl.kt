package com.eduramza.redditapp.postdetails.repository

import com.eduramza.redditapp.domain.detail.DetailRootResponse
import com.eduramza.redditapp.utils.PostListGenericException
import com.eduramza.redditapp.service.RedditServiceApi
import com.eduramza.redditapp.utils.setCorrectJsonLink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DetailRepositoryImpl(private val api: RedditServiceApi) : DetailRepository {

    override suspend fun fetchSelectedPost(permalink: String): Flow<Result<DetailRootResponse>> {
        return flow {
            emit(Result.success(api.getPostDetails(permalink.setCorrectJsonLink())))
        }.catch { ex ->
            emit(Result.failure(
                PostListGenericException("${ex.message} There was a problem when try get details")
            ))
        }
    }
}