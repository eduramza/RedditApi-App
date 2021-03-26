package com.eduramza.redditapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object {

        private const val BASE_URL = "https://www.reddit.com/r/"

        fun createRetrofitService(): RedditServiceApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RedditServiceApi::class.java)
    }
}