package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.utils.PostListGenericException
import com.eduramza.redditapp.postlist.mapper.PostsMapper
import com.eduramza.redditapp.service.RedditServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PostsRepositoryImpl(
    private val api: RedditServiceApi,
    private val mapper: PostsMapper
) : PostsRepository {

    override suspend fun fetchPosts(topic: String): Flow<Result<List<PostsDTO>>> {
        return flow {
            val result = api.getListOfPost(topic)
            emit(Result.success(mapper.mapperResponseToView(result)))
        }.catch {
            emit(Result.failure(
                PostListGenericException("${it.message} There was a problem with the listing of posts")
            ))
        }
    }

    override suspend fun fetchNextPage(topic: String, afterPage: String): Flow<Result<List<PostsDTO>>> {
        return flow {
            val result = api.getListNextPage(topic, afterPage)
            emit(Result.success(mapper.mapperResponseToView(result)))
        }.catch {
            emit(Result.failure(
                    PostListGenericException("${it.message} There was a problem with the listing of posts")
            ))
        }
    }
}