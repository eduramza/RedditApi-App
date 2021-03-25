package com.eduramza.redditapp.postlist.repository

import com.eduramza.redditapp.domain.PostsResponse
import com.eduramza.redditapp.postlist.service.RedditServiceApi
import com.eduramza.redditapp.utils.BaseTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PostsRepositoryTest: BaseTest(){

    private lateinit var repository: PostsRepository
    private val api: RedditServiceApi = mock()

    @Test
    fun shouldCallApi() = runBlockingTest {
        repository = PostsRepositoryImpl(api)

        repository.fetchPosts()

        verify(api).getListOfPost()
    }

}