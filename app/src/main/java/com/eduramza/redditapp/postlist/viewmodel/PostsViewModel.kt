package com.eduramza.redditapp.postlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.repository.PostsRepository

class PostsViewModel(private val repository: PostsRepository) {

    private val _posts = MutableLiveData<Result<List<PostsDTO>>>()
    val posts: LiveData<Result<List<PostsDTO>>>
        get() =_posts

    init {
        repository.fetchPosts()
    }
}