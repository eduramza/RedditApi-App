package com.eduramza.redditapp.postlist.mapper

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.domain.PostsResponse

class PostsMapper {

    fun mapperResponseToView(response: PostsResponse): List<PostsDTO>{
        return emptyList()
    }
}
