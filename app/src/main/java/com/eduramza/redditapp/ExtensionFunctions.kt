package com.eduramza.redditapp

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun Double.getRelativeTimeStamp(): String {
    return "10m ago"
}

fun ImageView.downloadImageFromUrl(context: Context, url: String){
    if (url != "null") {
        Glide.with(context)
            .load(url).into(this)
    }
}
fun String.setCorrectJsonLink(): String{
    return "${this.substring(0, this.lastIndexOf("/"))}.json"
}