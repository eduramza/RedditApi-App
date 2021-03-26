package com.eduramza.redditapp.postlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.R
import com.eduramza.redditapp.domain.PostsDTO
import com.eduramza.redditapp.postlist.view.adapter.PostAdapter
import com.eduramza.redditapp.postlist.viewmodel.PostsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val viewModel: PostsViewModel by viewModel()

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

        setupObservers()

        return view
    }

    private fun getMockedList(): MutableList<PostsDTO> {
        return mutableListOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO Implement clickListener
    }

    private fun setupObservers(){
        viewModel.posts.observe(this as LifecycleOwner, { posts ->
            if (posts.isSuccess && posts.getOrNull() != null){
                //TODO IMPLEMENT ADAPTER
            } else {
                //TODO ERROR SCREEN
            }
        } )
    }
}