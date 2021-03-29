package com.eduramza.redditapp.postlist.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.GONE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.redditapp.R
import com.eduramza.redditapp.databinding.ListFragmentBinding
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.postlist.view.adapter.PostAdapter
import com.eduramza.redditapp.postlist.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.lv_search
import kotlinx.android.synthetic.main.details_post_fragment.*
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class ListFragment : Fragment() {

    private lateinit var adapter: PostAdapter
    private val viewModel: PostsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ListFragmentBinding.bind(view)

        adapter = PostAdapter(mutableListOf(), requireContext()) {
            val action = ListFragmentDirections.openDetailsFragment(it)
            findNavController().navigate(action)
        }

        binding.recyclerviewPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewPosts.adapter = adapter

        setupObservers()
    }

    private fun setupObservers(){
        viewModel.posts.observe(this as LifecycleOwner, { posts ->
            if (posts.isSuccess && posts.getOrNull() != null) {
                adapter.updateList(posts.getOrNull()!! as MutableList<PostsDTO>)
            } else {
                //TODO ERROR SCREEN
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        searchView.apply {
            queryHint = "Search SubReddit"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.getPostList(query!!)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

}