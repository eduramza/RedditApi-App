package com.eduramza.redditapp.postlist.mapper

import com.eduramza.redditapp.domain.list.ListPostResponse
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.relativetime.LanguageType
import com.eduramza.relativetime.RelativeTimeAgo

class PostsMapper {

    fun mapperResponseToView(response: ListPostResponse): List<PostsDTO>{
        val after = response.data.after
        return response.data.children.map {
            PostsDTO(
                    title = it.data.title,
                    author = it.data.author,
                    elapsedTime = RelativeTimeAgo.relativeUnixTimePast(it.data.createdUtc.toLong()),
                    thumbnailUrl = getThumbnailUrl(it.data.thumbnail,
                                        it.data.secureMedia?.oembed?.thumbnailUrl).toString(),
                    permalink = it.data.permalink,
                    numComments = it.data.numComments,
                    after = after
            )
        }
    }

    private fun getThumbnailUrl(thumbnail: String,
                                thumbnailUrl: String?): String?{
        return if (thumbnail.isNullOrBlank()) thumbnailUrl else thumbnail
    }

}
