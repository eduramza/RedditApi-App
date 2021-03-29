package com.eduramza.redditapp

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val DAYS_ON_THE_YEAR = 365
const val DAYS_ON_THE_MONTH = 30

fun Double.getRelativeTimeStamp(): String {
    val date = Date(this.toLong()*1000L)
    val now = System.currentTimeMillis()
    return timeAgo(date.time, now)
}

fun timeAgo(createdUtc: Long, now: Long): String {
    val diff = now - createdUtc

    val toDays = TimeUnit.MILLISECONDS.toDays(diff)
    if (toDays > 0){
        return returnDays(toDays)
    }

    val toHours = TimeUnit.MILLISECONDS.toHours(diff)
    if (toHours > 0){
        return "$toHours hours ago"
    }

    val toMinutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    return if (toMinutes > 0){
        "$toMinutes minutes ago"
    } else {
        "just now"
    }
}

private fun returnDays(toDays: Long): String {
    return when {
        toDays > 365 -> {
            return "${getRelativeAgo(toDays, DAYS_ON_THE_YEAR)} year(s) ago"
        }
        toDays > 30 -> {
            return "${getRelativeAgo(toDays, DAYS_ON_THE_MONTH)} month(s) ago"
        }
        toDays < 30 -> {
            return "$toDays day(s) ago"
        }
        else -> ""
    }
}

fun getRelativeAgo(toDays: Long, period: Int) = toDays / period

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