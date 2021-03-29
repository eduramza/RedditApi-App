package com.eduramza.redditapp.di

import com.eduramza.redditapp.postdetails.repository.DetailRepository
import com.eduramza.redditapp.postdetails.repository.DetailRepositoryImpl
import com.eduramza.redditapp.postdetails.viewmodel.DetailViewModel
import com.eduramza.redditapp.postlist.mapper.PostsMapper
import com.eduramza.redditapp.postlist.repository.PostsRepository
import com.eduramza.redditapp.postlist.repository.PostsRepositoryImpl
import com.eduramza.redditapp.service.RetrofitConfig
import com.eduramza.redditapp.postlist.viewmodel.PostsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { PostsMapper() }
    single { RetrofitConfig.createRetrofitService() }
    single<PostsRepository> { PostsRepositoryImpl(get(), get()) }
    viewModel { PostsViewModel(get()) }

    single<DetailRepository> { DetailRepositoryImpl(get()) }
    viewModel { DetailViewModel(get()) }
}