package com.eduramza.redditapp.domain.list

data class PostsDTO(
    val title: String,
    val author: String,
    val elapsedTime: String,
    val thumbnailUrl: String,
    val permalink: String
)