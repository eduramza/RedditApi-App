package com.eduramza.redditapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object {

        private const val BASE_URL = "https://www.reddit.com/"
        private val httpClient = OkHttpClient.Builder().addInterceptor(LoggingInterceptor())

        fun createRetrofitService(): RedditServiceApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(RedditServiceApi::class.java)
    }
}