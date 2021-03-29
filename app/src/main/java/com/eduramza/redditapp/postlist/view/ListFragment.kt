package com.eduramza.redditapp.postlist.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.redditapp.R
import com.eduramza.redditapp.databinding.ListFragmentBinding
import com.eduramza.redditapp.domain.list.PostsDTO
import com.eduramza.redditapp.postlist.view.adapter.PostAdapter
import com.eduramza.redditapp.postlist.viewmodel.PostsViewModel
import com.eduramza.redditapp.utils.setCorrectShareLink
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.details_post_fragment.*
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class ListFragment : Fragment(), PostAdapter.PostAdapterListener {

    private lateinit var adapter: PostAdapter
    private val viewModel: PostsViewModel by viewModel()
    private var _binding: ListFragmentBinding? = null
    private var defaultTopic = "news"
    private var afterPage: String = ""

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
        _binding = ListFragmentBinding.bind(view)

        adapter = PostAdapter(mutableListOf(), requireContext(), this)

        _binding?.let{
            val llm = LinearLayoutManager(requireContext())
            it.recyclerviewPosts.layoutManager = llm
            it.recyclerviewPosts.adapter = adapter

            recyclerViewListenerSetup(it, llm)
        }
        setupObservers()

    }

    private fun setupObservers(){
        viewModel.getPostList(defaultTopic)
        viewModel.posts.observe(this as LifecycleOwner, { posts ->
            if (posts.isSuccess && posts.getOrNull() != null) {
                adapter.updateList(posts.getOrNull()!! as MutableList<PostsDTO>)
                afterPage = posts.getOrNull()!![0].after
                showList()
            } else {
                showError()
            }
        })
        viewModel.loading.observe(this as LifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
        viewModel.next.observe(this as LifecycleOwner, {
            if (it.isSuccess && it.getOrNull() != null) {
                adapter.addList(it.getOrNull() as MutableList<PostsDTO>)
                afterPage = it.getOrNull()!![0].after
            }
        })
    }

    private fun showList(){
        _binding?.let {
            it.recyclerviewPosts.visibility = VISIBLE
        }
    }

    private fun showLoading(){
        _binding?.let {
            it.loading.visibility = VISIBLE
            it.containerError.root.visibility = GONE
            it.recyclerviewPosts.visibility = GONE
        }
    }

    private fun hideLoading(){
        _binding?.let {
            it.loading.visibility = GONE
        }
    }

    private fun showError() {
        _binding?.let {
            it.containerError.root.visibility = VISIBLE
            it.recyclerviewPosts.visibility = GONE
        }
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
                    defaultTopic = query!!
                    viewModel.getPostList(defaultTopic)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun recyclerViewListenerSetup(it: ListFragmentBinding, llm: LinearLayoutManager) {
        it.recyclerviewPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = llm.childCount
                val totalItemCount: Int = llm.itemCount
                val firstVisibleItemPosition: Int = llm.findFirstVisibleItemPosition()

                if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    viewModel.getNextPage(defaultTopic, afterPage)
                }
            }
        })
    }

    override fun clickItem(permalink: String) {
        val action = ListFragmentDirections.openDetailsFragment(permalink)
        findNavController().navigate(action)
    }

    override fun shareLink(permalink: String) {
        ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setChooserTitle("Share Reddit Post")
                .setText(permalink.setCorrectShareLink())
                .startChooser()
    }

}