/*
 * ImageViewExtension.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.utils.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.myapplication.R

fun ImageView.loadImageUrl(
    url: String?,
    @DrawableRes placeholderId: Int? = R.drawable.ic_ugamer_vertical_gray
) {
    var builder = Glide.with(context).load(url)
    builder = placeholderId?.let(builder::placeholder) ?: builder
    builder.into(this)
}

fun ImageView.loadImageResource(
    @DrawableRes resourceId: Int?,
    @DrawableRes placeholderId: Int? = R.drawable.ic_ugamer_vertical_gray
) {
    var builder = Glide.with(context).load(resourceId)
    builder = placeholderId?.let(builder::placeholder) ?: builder
    builder.into(this)
}
