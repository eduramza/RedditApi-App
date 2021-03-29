package com.eduramza.redditapp

import android.app.Application
import com.eduramza.redditapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RedditApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RedditApplication)
            modules(listOf(appModule))
        }
    }
}