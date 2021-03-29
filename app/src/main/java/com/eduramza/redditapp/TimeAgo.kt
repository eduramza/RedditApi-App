package com.eduramza.redditapp

import java.time.Instant
import java.util.concurrent.TimeUnit

/**
 * reference: https://stackoverflow.com/questions/3859288/how-to-calculate-time-ago-in-java
 */
class TimeAgo {
    companion object{
        val times = listOf(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1)
        )

        private val timesString = listOf("year", "month", "day", "hour", "minute", "second")

        fun toDuration(duration: Long): String {
            val sb = StringBuffer()

            for (i in times.indices) {
                val current: Long = times[i]
                val temp = duration / current
                if (temp > 0) {
                    sb.append(temp).append(" ").append(timesString[i])
                        .append(if (temp != 1L) "s" else "").append(" ago")
                    break
                }
            }
            return if("" == sb.toString())
                "0 seconds ago";
            else
                sb.toString()
        }
    }

}