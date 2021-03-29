package com.eduramza.redditapp.postlist.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.databinding.PostItemBinding
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.utils.downloadImageFromUrl

class PostAdapter(
    private val postList: MutableList<PostsDTO>,
    private val context: Context,
    private val listener: PostAdapterListener) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            PostItemBinding
                .inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = postList[position]
        with(holder){
            binding.tvPostedByInfo.text = item.author
            binding.tvElapsedTime.text = item.elapsedTime
            binding.tvPostTitle.text = item.title
            binding.imgThumbnail.downloadImageFromUrl(context, item.thumbnailUrl)
            binding.tvCommentsCount.text = item.numComments.toString()

            binding.containerPostItem.setOnClickListener { listener.clickItem(item.permalink) }
            binding.imgShareIcon.setOnClickListener { listener.shareLink(item.permalink) }

            binding.imgThumbnail.contentDescription = "Image of ${item.title}"
        }
    }

    override fun getItemCount() = postList.size

    fun updateList(items: List<PostsDTO>){
        postList.clear()
        postList.addAll(items)
        notifyDataSetChanged()
    }

    fun addList(items: List<PostsDTO>){
        postList.addAll(items)
        val listSet = postList.toSet()
        postList.clear()
        postList.addAll(listSet)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root)

    interface PostAdapterListener{
        fun clickItem(permalink: String)
        fun shareLink(permalink: String)
    }
}