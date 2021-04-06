package com.eduramza.redditapp.postdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.databinding.CommentItemBinding
import com.eduramza.redditapp.domain.detail.Comment
import com.eduramza.redditapp.domain.detail.DetailRootResponse.PostDetailData
import com.eduramza.relativetime.RelativeTimeAgo
import com.google.gson.internal.LinkedTreeMap
import java.util.ArrayList

class CommentsAdapter(
    private val comments: MutableList<PostDetailData.Data.Children>,
    private val detailListener: (String) -> Unit)
    : RecyclerView.Adapter<CommentsAdapter.ViewHolder>(
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
        val itemBinding = CommentItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {

        with(holder){
            val comment = comments[position].data
            if (comment.replies !is String){

                val (authorR, bodyR) = getReplies(comment)

                binding.tvCommentReplyAuthor.text = authorR
                binding.tvCommentReplyBody.text = bodyR
            }

            "${comment.author} ${RelativeTimeAgo.relativeUnixTimePast(comment.createdUtc.toLong())}".also {
                binding.tvCommentAuthorAndElapsed.text = it
            }

            binding.tvCommentBody.text = comment.body
            binding.tvRedirectToThread.setOnClickListener {
                detailListener(comment.permalink)
            }
        }
    }

    private fun getReplies(comment: PostDetailData.Data.Children.PostDetail): Pair<String, String> {
        val lv1 = comment.replies as LinkedTreeMap<Object, Object>
        val lv2 = lv1.get("data") as LinkedTreeMap<Object, Object>
        val lv3 = lv2.get("children") as ArrayList<Comment.Data.Children>
        val children = lv3[0] as LinkedTreeMap<Object, Object>
        val children1 = children.get("data") as LinkedTreeMap<Object, Object>
        val authorT = children1.get("author").toString()
        val bodyT = children1.get("body").toString()
        val createdUtc: Double? = children1.get("created_utc") as Double?
        return if(authorT == "null") Pair("", "") else {
            Pair("$authorT ${RelativeTimeAgo.relativeUnixTimePast(createdUtc!!.toLong())}", bodyT )
        }
    }

    override fun getItemCount() = comments.size

    fun updateList(list: MutableList<PostDetailData.Data.Children>){
        comments.clear()
        comments.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CommentItemBinding): RecyclerView.ViewHolder(binding.root)

}
