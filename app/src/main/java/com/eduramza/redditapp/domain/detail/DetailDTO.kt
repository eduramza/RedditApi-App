package com.eduramza.redditapp.domain.detail

data class DetailDTO(
    val kind: String,
    val postAuthorAndElapsed: String,
    var title: String? = "",
    var thumbnail: String? = "",
    var permalink: String? = "",
    val replies: RepliesDTO
) {
    data class RepliesDTO (
        val authorAndElapsed: String,
        val body: String
    )
}
