package com.eduramza.redditapp.postlist.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduramza.redditapp.R
import com.eduramza.redditapp.databinding.PostItemBinding
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.downloadImageFromUrl

class PostAdapter(
    private val postList: MutableList<PostsDTO>,
    private val context: Context,
    private val listener: (String) -> Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

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

            binding.containerPostItem.setOnClickListener { listener(item.permalink) }
            binding.imgThumbnail.contentDescription = "Image of ${item.title}"
        }
    }

    override fun getItemCount() = postList.size

    fun updateList(items: List<PostsDTO>){
        postList.clear()
        postList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root) { }
}