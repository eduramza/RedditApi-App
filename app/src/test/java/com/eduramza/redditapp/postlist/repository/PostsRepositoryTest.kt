package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.domain.PostsResponse
import com.eduramza.redditapp.postlist.mapper.PostsMapper
import com.eduramza.redditapp.service.RedditServiceApi
import com.eduramza.redditapp.utils.BaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class PostsRepositoryTest: BaseTest(){

    private val mapper: PostsMapper = mock()
    private val postsApiResponse: PostsResponse = mock()
    private val postsDTO: List<PostsDTO> = mock()
    private lateinit var repository: PostsRepository
    private val api: RedditServiceApi = mock()

    @Test
    fun shouldCallApiAndMapper() = runBlockingTest {

        mockSuccessResult()

        repository.fetchPosts(anyString()).first()

        verify(api).getListOfPost(anyString())
        verify(mapper).mapperResponseToView(any())
    }

    @Test
    fun shouldCallObjectMapperAndReturnNewListItemToViewModel() = runBlockingTest {

        mockSuccessResult()

        assertEquals(Result.success(postsDTO), repository.fetchPosts(anyString()).first())
    }

    @Test
    fun shouldReturnErrorWhenApiCallInTroubleAndTheMapperShouldNotBeCalled() = runBlockingTest {
        mockApiError()

        val result = repository.fetchPosts(anyString()).first().exceptionOrNull()?.message

        verify(mapper, times(0)).mapperResponseToView(any())
        assertEquals(genericError.message, result)
    }

    @Test
    fun shouldReturnExceptionWithMapperWasProblemAndApiMustBeCalled() = runBlockingTest {
        mockMapperError()

        val result = repository.fetchPosts(anyString()).first().exceptionOrNull()?.message

        verify(api).getListOfPost(anyString())
        assertEquals(genericError.message, result)
    }

    private fun mockApiError() {
        whenever(mapper.mapperResponseToView(postsApiResponse)).thenReturn(postsDTO)
        whenever(api.getListOfPost(anyString())).thenThrow(genericError)

        repository = PostsRepositoryImpl(api, mapper)
    }

    private fun mockMapperError() {
        whenever(api.getListOfPost(anyString())).thenReturn(postsApiResponse)
        whenever(mapper.mapperResponseToView(any())).thenThrow(genericError)

        repository = PostsRepositoryImpl(api, mapper)
    }

    private fun mockSuccessResult() {
        whenever(api.getListOfPost(anyString())).thenReturn(postsApiResponse)
        whenever(mapper.mapperResponseToView(postsApiResponse)).thenReturn(postsDTO)

        repository = PostsRepositoryImpl(api, mapper)
    }

}