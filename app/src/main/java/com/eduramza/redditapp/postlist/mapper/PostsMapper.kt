package com.eduramza.redditapp.postlist.mapper

import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.domain.list.PostsResponse

class PostsMapper {

    fun mapperResponseToView(response: PostsResponse): List<PostsDTO>{
        return response.data.children.map {
            PostsDTO(title = it.data.title,
                author = it.data.author,
                elapsedTime = it.data.createdUtc.toString(),
                thumbnailUrl = it.data.secureMedia?.oembed?.thumbnailUrl.toString(),
                permalink = it.data.permalink)
        }
    }
}
