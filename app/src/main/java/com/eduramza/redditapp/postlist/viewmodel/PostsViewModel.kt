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

    fun getPostList(q: String){
        viewModelScope.launch {
            repository.fetchPosts(q)
                .collect {
                    _posts.value = it
                }
        }
    }

}