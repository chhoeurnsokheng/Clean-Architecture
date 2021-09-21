/*
 * BadgeDrawableExtension.kt
 * UGamer
 *
 * Created by Sopheak Te on 09/03/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.utils.extension

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.google.android.material.badge.BadgeDrawable

fun BadgeDrawable.applyDefaultConfiguration(context: Context) {
    backgroundColor = ContextCompat.getColor(context, R.color.black)
    badgeGravity = BadgeDrawable.TOP_END
    maxCharacterCount = 3
    verticalOffset = 2.dp
    horizontalOffset = 2.dp
}
