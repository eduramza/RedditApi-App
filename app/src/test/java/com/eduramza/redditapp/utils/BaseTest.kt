package com.eduramza.redditapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eduramza.redditapp.postlist.PostListGenericException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
open class BaseTest {
    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    val genericError = PostListGenericException("there was a problem with the listing of posts")
}