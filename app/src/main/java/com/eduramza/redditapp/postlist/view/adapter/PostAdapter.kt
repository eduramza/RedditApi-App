package com.eduramza.redditapp.postlist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.Posts
import com.eduramza.redditapp.R

class PostAdapter(
    private val posts: List<Posts>,
    private val listener: (String) -> Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun getItemCount() = posts.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //TODO IMPLEMENT VIEW HOLDER

    }
}