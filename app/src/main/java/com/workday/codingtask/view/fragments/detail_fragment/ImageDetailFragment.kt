package com.workday.codingtask.view.fragments.detail_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.workday.codingtask.R
import com.workday.codingtask.data.TrendingImage
import com.workday.codingtask.databinding.FragmentDetailBinding


/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
class ImageDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val imageDetail: TrendingImage? by lazy {
        arguments?.getParcelable<TrendingImage>("image")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
        Inflate the layout using dataBinding util method i.e.
        DataBindingUtil.
         */
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail,
            container, false
        )

        setupUI()

        return binding.root
    }

    private fun setupUI() {

        imageDetail?.let{ binding.model = it}
    }

}
