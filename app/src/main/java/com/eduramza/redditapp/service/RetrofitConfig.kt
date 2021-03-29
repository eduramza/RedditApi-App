package com.eduramza.redditapp.service

import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.reddit.com/"
val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

class RetrofitConfig {
    companion object {

        fun createRetrofitService(): RedditServiceApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RedditServiceApi::class.java)
    }
}