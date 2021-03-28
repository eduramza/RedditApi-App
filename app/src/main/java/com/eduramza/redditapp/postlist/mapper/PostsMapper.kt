package com.eduramza.redditapp.postlist.mapper

import com.eduramza.redditapp.domain.list.ListPostResponse
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.getRelativeTimeStamp

class PostsMapper {

    fun mapperResponseToView(response: ListPostResponse): List<PostsDTO>{

        return response.data.children.map {
            PostsDTO(
                title = it.data.title,
                author = it.data.author,
                elapsedTime = it.data.createdUtc.getRelativeTimeStamp(),
                thumbnailUrl = it.data.thumbnail,
                //it.data.secureMedia?.oembed?.thumbnailUrl.toString(),
                permalink = it.data.permalink
            )
        }
    }

}
