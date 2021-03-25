package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.domain.PostsResponse
import com.eduramza.redditapp.postlist.mapper.PostsMapper
import com.eduramza.redditapp.postlist.service.RedditServiceApi
import com.eduramza.redditapp.utils.BaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PostsRepositoryTest: BaseTest(){

    private val mapper: PostsMapper = mock()
    private val postsApiResponse: PostsResponse = mock()
    private val postsDTO: List<PostsDTO> = mock()
    private lateinit var repository: PostsRepository
    private val api: RedditServiceApi = mock()

    @Test
    fun shouldCallApi() = runBlockingTest {

        mockSuccessResult()

        repository.fetchPosts().first()

        verify(api).getListOfPost()
        verify(mapper).mapperResponseToView(any())
    }

    @Test
    fun shouldReturnObjectMapper() = runBlockingTest {

        mockSuccessResult()

        assertEquals(Result.success(postsDTO), repository.fetchPosts().first())
    }

    private fun mockSuccessResult() {
        whenever(api.getListOfPost()).thenReturn(postsApiResponse)
        whenever(mapper.mapperResponseToView(postsApiResponse)).thenReturn(postsDTO)

        repository = PostsRepositoryImpl(api, mapper)
    }

}