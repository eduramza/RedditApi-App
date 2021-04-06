package com.eduramza.redditapp.domain.detail

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("children")
        val children: List<Children>
    ) {
        data class Children(
            @SerializedName("data")
            val `data`: Data
        ) {
            data class Data(
                @SerializedName("author")
                val author: String,
                @SerializedName("author_fullname")
                val authorFullname: String,
                @SerializedName("body")
                val body: String,
                @SerializedName("created")
                val created: Double,
                @SerializedName("created_utc")
                val createdUtc: Double,
                @SerializedName("id")
                val id: String,
                @SerializedName("parent_id")
                val parentId: String,
                @SerializedName("permalink")
                val permalink: String,
                @SerializedName("replies")
                val replies: Any?,
            )
        }
    }
}