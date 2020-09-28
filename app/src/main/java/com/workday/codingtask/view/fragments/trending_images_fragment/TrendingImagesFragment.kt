package com.workday.codingtask.view.fragments.trending_images_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.workday.codingtask.R
import com.workday.codingtask.data.TrendingImage
import com.workday.codingtask.databinding.FragmentListBinding
import com.workday.codingtask.viewmodel.ImagesViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.layout_error.*

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
class TrendingImagesFragment : Fragment() {

    private val imagesViewModel: ImagesViewModel by activityViewModels()
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
        Inflate the layout using dataBinding util method i.e.
        DataBindingUtil.
         */
        val binding: FragmentListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list,
            container, false
        )

        return binding.run {

            viewModel = imagesViewModel
            lifecycleOwner = viewLifecycleOwner
            root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
        setupUI()
    }

    // initialize the recycleView with empty list
    private fun setupUI() {

        imagesAdapter = ImagesAdapter().apply {

            update(imagesViewModel.images.value ?: emptyList())
        }

        with(recyclerView)
        {
            layoutManager = GridLayoutManager(context, 3)
            adapter = imagesAdapter
        }
    }

    private fun setupViewModel() {

        with(imagesViewModel)
        {
            images.observe(viewLifecycleOwner, renderImagesObserver)
            onError.observe(viewLifecycleOwner, errorObserver)
        }
    }

    //observers
    private val renderImagesObserver = Observer<List<TrendingImage>> {

        imagesAdapter.update(it)
    }

    private val errorObserver = Observer<Any> {

        layoutError.visibility = View.VISIBLE

        if (it.toString().trim().isNotEmpty())
            textViewError.text = it.toString()
    }

}
