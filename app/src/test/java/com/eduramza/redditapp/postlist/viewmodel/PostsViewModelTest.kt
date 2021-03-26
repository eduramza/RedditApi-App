package com.eduramza.redditapp.postlist.viewmodel

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.repository.PostsRepository
import com.eduramza.redditapp.utils.BaseTest
import com.eduramza.redditapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PostsViewModelTest : BaseTest() {

    private val repository: PostsRepository = mock()
    private val postsListMock = mock<List<PostsDTO>>()
    private val expected = Result.success(postsListMock)

    @Test
    fun shouldReturnPostsFromRepository() = runBlockingTest {

        val viewModel = successCaseInitialize()

        viewModel.posts.getValueForTest()

        verify(repository).fetchPosts()
    }

    @Test
    fun emitValueFromLiveDataFromRepository() = runBlockingTest {
        val viewModel = successCaseInitialize()

        assertEquals(expected, viewModel.posts.getValueForTest())
    }

    @Test
    fun emitErrorWhenRepositoryReturn() = runBlockingTest{
        val viewModel = mockErrorResponse()
        assertEquals(genericError, viewModel.posts.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockErrorResponse(): PostsViewModel {
        runBlocking {
            whenever(repository.fetchPosts()).thenReturn(
                flow {
                    emit(Result.failure<List<PostsDTO>>(genericError))
                }
            )
        }

        return PostsViewModel(repository)
    }

    private fun successCaseInitialize(): PostsViewModel {
        runBlocking {
            whenever(repository.fetchPosts()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        return PostsViewModel(repository)
    }

}