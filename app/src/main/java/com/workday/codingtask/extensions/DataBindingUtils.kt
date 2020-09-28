package com.workday.codingtask.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
@BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = true)
fun ImageView.imageUrl(url: String, drawable: Drawable) {
    this.load(url) {

        crossfade(true)
        placeholder(drawable)
        error(drawable)
    }
}