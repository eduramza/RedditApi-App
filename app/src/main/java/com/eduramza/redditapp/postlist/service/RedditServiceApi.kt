package com.eduramza.redditapp.postlist.service

import com.eduramza.redditapp.domain.PostsResponse

interface RedditServiceApi {
    fun getListOfPost(): PostsResponse
}
