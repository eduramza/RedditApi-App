package com.eduramza.redditapp.postdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.databinding.CommentItemBinding
import com.eduramza.redditapp.domain.detail.DetailRootResponse.PostDetailData

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
        val comment = comments[position].data
        //val replies = comment.replies.data.children[0].data

        with(holder){
            binding.tvCommentAuthorAndElapsed.text = comment.author
            binding.tvCommentBody.text = comment.body
           // binding.tvCommentReplyAuthor.text = replies.author
           // binding.tvCommentReplyBody.text = replies.body
            binding.tvRedirectToThread.setOnClickListener {
                detailListener("${comment.permalink}${comment.id}")
            }
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