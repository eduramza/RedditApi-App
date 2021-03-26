package com.eduramza.redditapp.postlist.mapper

import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.domain.PostsResponse

class PostsMapper {

    fun mapperResponseToView(response: PostsResponse): List<PostsDTO>{
        return response.data.children.map {
            PostsDTO(title = it.data.title,
                author = it.data.author,
                elapsedTime = it.data.created.toString(),
                thumbnailUrl = it.data.secureMedia?.oembed?.thumbnailUrl.toString())
        }
    }
}
