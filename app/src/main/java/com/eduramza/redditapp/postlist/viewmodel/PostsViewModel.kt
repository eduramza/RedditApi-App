package com.eduramza.redditapp.postlist.viewmodel

import androidx.lifecycle.*
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.postlist.repository.PostsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: PostsRepository) : ViewModel(){

    private val _posts = MutableLiveData<Result<List<PostsDTO>>>()
    val posts: LiveData<Result<List<PostsDTO>>>
        get() =_posts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _next = MutableLiveData<Result<List<PostsDTO>>>()
    val next: LiveData<Result<List<PostsDTO>>>
        get() =_posts

    fun getPostList(q: String){
        viewModelScope.launch {
            _loading.postValue(true)
            repository.fetchPosts(q)
                .collect {
                    _posts.value = it
                }
            _loading.postValue(false)
        }
    }

    fun getNextPage(topic: String, afterPage: String) {
        viewModelScope.launch {
            repository.fetchNextPage(topic, afterPage)
                    .collect{
                        _next.postValue(it)
                    }
        }
    }

}