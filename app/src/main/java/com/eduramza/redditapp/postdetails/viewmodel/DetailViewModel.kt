package com.eduramza.redditapp.postdetails.viewmodel

import androidx.lifecycle.*
import com.eduramza.redditapp.domain.detail.PostDetailResponse
import com.eduramza.redditapp.postdetails.repository.DetailRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DetailRepository): ViewModel() {

    private val _postDetail = MutableLiveData<Result<PostDetailResponse>>()

    val postDetail: LiveData<Result<PostDetailResponse>>
        get() = _postDetail

    fun getDetails(permalink: String){

        viewModelScope.launch {
            repository.fetchSelectedPost(permalink)
                .collect {
                    _postDetail.value = it
                }
        }

    }
}