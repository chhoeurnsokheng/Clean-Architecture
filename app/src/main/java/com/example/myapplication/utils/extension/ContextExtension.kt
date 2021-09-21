/*
 * ContextExtension.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.utils.extension

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.myapplication.R

inline val Context.layoutInflater: LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

val Context.normalSpace: Int
    get() = resources.getDimensionPixelSize(R.dimen.normal_space)

val Context.smallSpace: Int
    get() = resources.getDimensionPixelSize(R.dimen.small_space)

// fun Context.getQuantityStringZero(
//    @PluralsRes pluralResId: Int,
//    @StringRes zeroResId: Int,
//    pluralValue: Double
// ): String {
//    val value = ceil(pluralValue).toInt()
//    return if (value == 0) {
//        getString(zeroResId)
//    } else {
//        resources.getQuantityString(
//            pluralResId,
//            value,
//            NumberFormatter.formatNumber(pluralValue, 0)
//        )
//    }
// }

fun Context.getQuantityStringZero(
    @PluralsRes pluralResId: Int,
    @StringRes zeroResId: Int,
    pluralValue: Int
): String {
    return if (pluralValue == 0) {
        getString(zeroResId)
    } else {
        resources.getQuantityString(pluralResId, pluralValue, pluralValue)
    }
}

@ColorInt
fun Context.getColorById(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}
