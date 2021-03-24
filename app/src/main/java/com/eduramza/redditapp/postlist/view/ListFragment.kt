package com.eduramza.redditapp.postlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.Posts
import com.eduramza.redditapp.R
import com.eduramza.redditapp.postlist.view.adapter.PostAdapter

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerview_posts)

        with(recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PostAdapter(getMockedList()) {
                //TODO Add action to click list item
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }

        return view
    }

    private fun getMockedList(): List<Posts> {
        return listOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO Implement clickListener

    }
}