package com.eduramza.redditapp.postdetails.mapper

import com.eduramza.redditapp.domain.detail.Comment
import com.eduramza.redditapp.domain.detail.DetailDTO
import com.eduramza.redditapp.domain.detail.DetailRootResponse
import com.google.gson.Gson
import java.util.ArrayList

class DetailMapper {

    fun convertResponseToView(response: DetailRootResponse): List<DetailDTO>{
        val listDetail: MutableList<DetailDTO> = ArrayList()

        response.forEach {
            //o primeiro é o body e o segundo são os commentarios e replicas
            val lista = it.data.children.map { detail ->
                DetailDTO(kind = detail.kind,
                    postAuthorAndElapsed = "${detail.data.author} ${detail.data.createdUtc}",
                    title = detail.data.title,
                    thumbnail = detail.data.thumbnail,
                    permalink = detail.data.permalink,
                    replies = getReplies(detail.data.replies))
            }
            listDetail.addAll(lista)
        }
        return listDetail
    }

    private fun getReplies(replies: Any?): DetailDTO.RepliesDTO {
        if (replies is String || replies == null){
            return DetailDTO.RepliesDTO(authorAndElapsed = "", body = "")
        } else{
            //TODO convert response
            val jsonString = Gson().toJson(replies)
            val mappedReplie = Gson().fromJson(jsonString, Comment::class.java)
            return DetailDTO.RepliesDTO(authorAndElapsed = "", body = "")
        }
    }
}