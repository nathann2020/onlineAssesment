package com.workday.codingtask.view.fragments.trending_images_fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.workday.codingtask.data.TrendingImage
import com.workday.codingtask.databinding.RowImageBinding

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
class ImagesAdapter : RecyclerView.Adapter<ImagesViewHolder>() {

    private var trendingImages = ArrayList<TrendingImage>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ImagesViewHolder {
        val binding = RowImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ImagesViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val trendingImage = trendingImages[position]

        //render
        holder.bind(trendingImage)
    }

    override fun onBindViewHolder(
        holder: ImagesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            try {
                val image = payloads[0] as TrendingImage
                holder.bind(image)

            } catch (exception: java.lang.Exception) {
                Log.e("Exception", "onBindViewHolder: ", exception)
            }
        }
    }

    override fun getItemCount(): Int {
        return trendingImages.size
    }

    fun update(data: List<TrendingImage>) = if (trendingImages.isEmpty()) {
        trendingImages.addAll(data)
        notifyItemRangeInserted(0, trendingImages.size)
    } else {
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return trendingImages.size
            }

            override fun getNewListSize(): Int {
                return data.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return trendingImages[oldItemPosition] == data[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val newProduct = data[newItemPosition]
                val oldProduct = trendingImages[oldItemPosition]
                return newProduct.id == oldProduct.id
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                val newProduct = data[newItemPosition]
                val oldProduct = trendingImages[oldItemPosition]
                return if (newProduct != oldProduct) {
                    newProduct
                } else null
            }
        })
        trendingImages = data as ArrayList<TrendingImage>
        result.dispatchUpdatesTo(this)
    }
}