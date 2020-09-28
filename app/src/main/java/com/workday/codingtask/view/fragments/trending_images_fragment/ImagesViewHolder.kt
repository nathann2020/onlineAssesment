package com.workday.codingtask.view.fragments.trending_images_fragment

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.workday.codingtask.R
import com.workday.codingtask.data.TrendingImage
import com.workday.codingtask.databinding.RowImageBinding

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 *
 * CarViewHolder to hold an item view and metadata about its place within the RecyclerView.
 * Hold strong references to ViewHolder objects and that RecyclerView instances may hold
 * strong references to extra off-screen item views for caching purposes.
 * With a constructor which accepts `RowCarBinding` as the only parameter to bind it
 * with row_image layout.
 *
 */
class ImagesViewHolder(private val binding: RowImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(image: TrendingImage) {

        binding.model = image

        binding.root.setOnClickListener {
            val bundle = bundleOf("image" to image)
            it.findNavController().navigate(R.id.detailFragment, bundle)
        }

        binding.executePendingBindings()
    }
}