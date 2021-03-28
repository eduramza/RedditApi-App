package com.eduramza.redditapp.postdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import org.koin.android.viewmodel.ext.android.viewModel
import com.eduramza.redditapp.R
import com.eduramza.redditapp.databinding.DetailsPostFragmentBinding
import com.eduramza.redditapp.downloadImageFromUrl
import com.eduramza.redditapp.postdetails.viewmodel.DetailViewModel
import com.eduramza.redditapp.postlist.view.ListFragmentArgs

class DetailsFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private var _binding: DetailsPostFragmentBinding? = null
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_post_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DetailsPostFragmentBinding.bind(view)

        setupObservers(args.permalink)
    }

    private fun setupObservers(permalink: String){
        viewModel.getDetails(permalink)

        viewModel.postDetail.observe(this as LifecycleOwner, {
            if (it.isSuccess){
                val result = it.getOrNull()!![0].dataHeader.children[0].data
                _binding?.tvDetailTitle?.text = result.title
                _binding?.tvDetailAuthorAndElapsed?.text = result.author
                _binding?.imgDetailThumbnail?.downloadImageFromUrl(requireContext(), result.thumbnail)
            } else {
                //TODO TRHOWN ERROR
            }
        })
    }
}