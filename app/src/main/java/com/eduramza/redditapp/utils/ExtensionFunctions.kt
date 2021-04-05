package com.eduramza.redditapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.eduramza.redditapp.service.BASE_URL
import java.sql.Date
import java.util.concurrent.TimeUnit

fun ImageView.downloadImageFromUrl(context: Context, url: String){
    if (url != "null") {
        Glide.with(context)
                .load(url)
                .into(this)
    }
}
fun String.setCorrectJsonLink(): String{
    return "${this.substring(0, this.lastIndexOf("/"))}.json"
}

fun String.setCorrectShareLink(): String{
    return this.replaceFirst("/", BASE_URL)
}