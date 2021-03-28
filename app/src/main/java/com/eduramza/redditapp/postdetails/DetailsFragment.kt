package com.eduramza.redditapp.postdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import com.eduramza.redditapp.R
import com.eduramza.redditapp.databinding.DetailsPostFragmentBinding
import com.eduramza.redditapp.domain.detail.DetailRootResponse
import com.eduramza.redditapp.downloadImageFromUrl
import com.eduramza.redditapp.postdetails.adapter.CommentsAdapter
import com.eduramza.redditapp.postdetails.viewmodel.DetailViewModel
import com.eduramza.redditapp.postlist.view.ListFragmentArgs

const val KIND_POST_DETAIL_HEADER = "t3"

class DetailsFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private var _binding: DetailsPostFragmentBinding? = null
    private val args: ListFragmentArgs by navArgs()
    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_post_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DetailsPostFragmentBinding.bind(view)

        setupRecyclerView()

        setupObservers()
    }

    private fun setupRecyclerView() {
        commentsAdapter = CommentsAdapter(mutableListOf()) {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            activity?.startActivity(browser)
        }
        _binding?.let {
            it.rvComments.layoutManager = LinearLayoutManager(requireContext())
            it.rvComments.adapter = commentsAdapter
        }
    }

    private fun setupObservers(){
        viewModel.getDetails(args.permalink)

        viewModel.postDetail.observe(this as LifecycleOwner, {
            if (it.isSuccess){
                val resultHeader = it.getOrNull()!![0].data.children
                val resultBody = it.getOrNull()!![1].data.children

                updatePostBodyUI(resultHeader)
                commentsAdapter.updateList(resultBody
                        as MutableList<DetailRootResponse.PostDetailData.Data.Children>)
            } else {
                //TODO THROWN ERROR
            }
        })
    }

    private fun updatePostBodyUI(result: List<DetailRootResponse.PostDetailData.Data.Children>) {
        if( result[0].kind == KIND_POST_DETAIL_HEADER){
            _binding?.tvDetailTitle?.text = result[0].data.title
            _binding?.tvDetailAuthorAndElapsed?.text = result[0].data.author
            _binding?.imgDetailThumbnail?.downloadImageFromUrl(
                requireContext(), result[0].data.thumbnail)
        }
    }

}