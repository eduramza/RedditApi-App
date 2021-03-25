package com.eduramza.redditapp.postlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eduramza.redditapp.postlist.repository.PostsRepository
import com.eduramza.redditapp.utils.MainCoroutineScopeRule
import com.eduramza.redditapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {
    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    val viewModel : PostsViewModel
    val repository: PostsRepository = mock()

    init {
        viewModel = PostsViewModel(repository)
    }
    @Test
    fun shouldReturnPostsFromRepository(){
        viewModel.posts.getValueForTest()

        verify(repository).fetchPosts()
    }

}