package com.workday.codingtask.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
data class NetworkResponse(
    val data: List<TrendingImage>
)

@Parcelize
data class TrendingImage(
    val id: String,
    val images: ImageUrl?,
    val rating: String,
    val source: String,
    val source_tld: String,
    val username: String
) : Parcelable

@Parcelize
data class ImageUrl(
    val downsized: Downsized,
    val original: Original
) : Parcelable

@Parcelize
data class Original(
    val frames: String,
    val hash: String,
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
) : Parcelable

@Parcelize
data class Downsized(
    val height: String,
    val size: String,
    val url: String,
    val width: String
) : Parcelable