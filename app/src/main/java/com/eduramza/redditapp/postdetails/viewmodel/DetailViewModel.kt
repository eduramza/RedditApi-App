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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getDetails(permalink: String){

        viewModelScope.launch {
            _loading.postValue(true)
            repository.fetchSelectedPost(permalink)
                .collect {
                    _postDetail.value = it
                }
            _loading.postValue(false)
        }

    }
}