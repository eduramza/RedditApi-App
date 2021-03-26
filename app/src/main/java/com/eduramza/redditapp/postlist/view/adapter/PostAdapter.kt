package com.eduramza.redditapp.postlist.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduramza.redditapp.R
import com.eduramza.redditapp.domain.PostsDTO

class PostAdapter(
    private val postList: MutableList<PostsDTO>,
    private val context: Context,
    private val listener: (String) -> Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = postList[position]

        holder.postedBy.text = item.author
        holder.elapsedTime.text = item.elapsedTime
        holder.postTitle.text = item.title
        if (item.thumbnailUrl != "null"){
            Log.d("ImageView", item.thumbnailUrl)
            Glide.with(context)
                .load(item.thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.postMedia)
        }
    }

    override fun getItemCount() = postList.size

    fun updateList(items: List<PostsDTO>){
        postList.clear()
        postList.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val postedBy: TextView = view.findViewById(R.id.tv_posted_by_info)
        val elapsedTime: TextView = view.findViewById(R.id.tv_elapsed_time)
        val postTitle: TextView = view.findViewById(R.id.tv_post_title)
        val postMedia: ImageView = view.findViewById(R.id.img_thumbnail)
    }
}