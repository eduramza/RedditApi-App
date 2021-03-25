package com.eduramza.redditapp.postlist.viewmodel

import androidx.lifecycle.*
import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.repository.PostsRepository

class PostsViewModel(private val repository: PostsRepository) : ViewModel(){

    private val _posts = liveData {
        emitSource(repository.fetchPosts().asLiveData())
    }
    val posts: LiveData<Result<List<PostsDTO>>>
        get() =_posts

}