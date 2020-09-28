package com.workday.codingtask.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.workday.codingtask.R
import com.workday.codingtask.databinding.ActivityTrendingImagesBinding
import com.workday.codingtask.viewmodel.ImagesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
class ImagesActivity : AppCompatActivity() {

    private val model by viewModel<ImagesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
        Inflate the layout using dataBinding util method i.e.
        DataBindingUtil.
         */
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_trending_images
        ) as ActivityTrendingImagesBinding

        model.loadImages()
    }
}