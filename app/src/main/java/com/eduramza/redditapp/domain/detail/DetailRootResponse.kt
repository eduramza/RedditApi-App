package com.eduramza.redditapp.domain.detail

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class DetailRootResponse: ArrayList<DetailRootResponse.PostDetailData>() {
    data class PostDetailData(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("kind")
        val kind: String
    ) {
        data class Data(
            @SerializedName("after")
            val after: Any?,
            @SerializedName("before")
            val before: Any?,
            @SerializedName("children")
            val children: List<Children>,
            @SerializedName("dist")
            val dist: Any?,
            @SerializedName("modhash")
            val modhash: String
        ) {
            data class Children(
                @SerializedName("data")
                val data: PostDetail,
                @SerializedName("kind")
                val kind: String
            ) {
                data class PostDetail(
                    @SerializedName("author")
                    val author: String,
                    @SerializedName("title")
                    val title: String,
                    @SerializedName("body")
                    val body: String,
                    @SerializedName("permalink")
                    val permalink: String,
                    @SerializedName("created")
                    val created: Double,
                    @SerializedName("created_utc")
                    val createdUtc: Double,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("parent_id")
                    val parentId: String,
                    @SerializedName("replies")
                    val replies: Any?,
                    @SerializedName("subreddit")
                    val subreddit: String,
                    @SerializedName("subreddit_id")
                    val subredditId: String,
                    @SerializedName("subreddit_type")
                    val subredditType: String,
                    @SerializedName("thumbnail")
                    val thumbnail: String,
                )
            }
        }
    }

}