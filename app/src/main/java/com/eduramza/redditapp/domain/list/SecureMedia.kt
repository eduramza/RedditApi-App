package com.eduramza.redditapp.domain.list

import com.google.gson.annotations.SerializedName

data class SecureMedia(
    val type: String,
    val oembed: Oembed,
) {
    data class Oembed(
        @SerializedName("author_name")
        val authorName: String,
        @SerializedName("author_url")
        val authorUrl: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("html")
        val html: String,
        @SerializedName("provider_name")
        val providerName: String,
        @SerializedName("provider_url")
        val providerUrl: String,
        @SerializedName("thumbnail_height")
        val thumbnailHeight: Int,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
        @SerializedName("thumbnail_width")
        val thumbnailWidth: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("version")
        val version: String,
        @SerializedName("width")
        val width: Int
    )
}
