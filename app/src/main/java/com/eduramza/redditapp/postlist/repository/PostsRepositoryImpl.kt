package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.PostListGenericException
import com.eduramza.redditapp.postlist.mapper.PostsMapper
import com.eduramza.redditapp.postlist.service.RedditServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PostsRepositoryImpl(
    private val api: RedditServiceApi,
    private val mapper: PostsMapper
) : PostsRepository {

    override suspend fun fetchPosts(): Flow<Result<List<PostsDTO>>> {
        return flow {
            val result = api.getListOfPost()
            emit(Result.success(mapper.mapperResponseToView(result)))
        }.catch {
            emit(Result.failure(
                PostListGenericException("there was a problem with the listing of posts")
            ))
        }
    }
}