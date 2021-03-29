package com.eduramza.redditapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {
    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    val genericError = PostListGenericException("there was a problem with the listing of posts")
}