package com.eduramza.redditapp.postlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.repository.PostsRepository
import com.eduramza.redditapp.utils.MainCoroutineScopeRule
import com.eduramza.redditapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PostsViewModelTest {
    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    private val repository: PostsRepository = mock()
    private val postsListMock = mock<List<PostsDTO>>()
    private val expected = Result.success(postsListMock)
    private val exception = RuntimeException("Something wrong")

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
        assertEquals(exception, viewModel.posts.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockErrorResponse(): PostsViewModel {
        runBlocking {
            whenever(repository.fetchPosts()).thenReturn(
                flow {
                    emit(Result.failure<List<PostsDTO>>(exception))
                }
            )
        }

        val viewModel = PostsViewModel(repository)
        return viewModel
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