package com.eduramza.redditapp.postdetails.viewmodel

import androidx.lifecycle.*
import com.eduramza.redditapp.domain.detail.DetailRootResponse
import com.eduramza.redditapp.postdetails.repository.DetailRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DetailRepository): ViewModel() {

    private val _postDetail = MutableLiveData<Result<DetailRootResponse>>()

    val postDetail: LiveData<Result<DetailRootResponse>>
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