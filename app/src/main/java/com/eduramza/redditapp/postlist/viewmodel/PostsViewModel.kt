package com.eduramza.redditapp.postlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.repository.PostsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: PostsRepository) : ViewModel(){

    private val _posts = MutableLiveData<Result<List<PostsDTO>>>()
    val posts: LiveData<Result<List<PostsDTO>>>
        get() =_posts

    init {
        viewModelScope.launch {
            repository.fetchPosts()
                .collect {
                    _posts.value = it
                }
        }

    }
}